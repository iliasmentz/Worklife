package com.linkedin.service;

import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.recommendation.UserInfoBo;
import info.debatty.java.lsh.SuperBit;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomePostService {
	private final LoginRepository loginRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final FileService fileService;
	private final PostRepository postRepository;

	@Autowired
	public HomePostService(LoginRepository loginRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService, PostRepository postRepository) {
		this.loginRepository = loginRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.fileService = fileService;
		this.postRepository = postRepository;
	}

	public Set<Long> getUserPosts(Long userId) {
		Map<Long, List<UserInfoBo>> allUsersDatabaseVector = userRepository.getAllUsersVector()
				.stream()
				.collect(Collectors.groupingBy(UserInfoBo::getUserId));
		if (!allUsersDatabaseVector.containsKey(userId)) {
			System.out.println("Can't find a vector for the current user");
			return new HashSet<>();
		}

		List<Long> allUsersIds = userRepository.findAll().stream().map(x -> x.getId().longValue()).collect(Collectors.toList());
		List<Long> allPostIds = postRepository.findAll().stream().map(x -> x.getPostId().longValue()).collect(Collectors.toList());

		Map<Long, double[]> usersVector = createAllUsersVectors(allUsersIds, allPostIds, allUsersDatabaseVector);

		double[] askedUserVector = usersVector.get(userId);
		SuperBit sb = new SuperBit(allPostIds.size());

		boolean[] askedUserSignature = sb.signature(askedUserVector);


		Set<Long> postIds = usersVector.entrySet()
				.stream()
				.filter(x -> !x.getKey().equals(userId))
				.map(x -> Pair.of(x.getKey(), calculateSignatureEntry(x, askedUserSignature, sb)))
				.sorted(Comparator.comparing(Pair::getSecond))
				.map(x -> allUsersDatabaseVector.get(x.getFirst()))
				.map(x -> excluedeCommonPosts(x, allUsersDatabaseVector.get(userId)))
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());

		return postIds;
	}

	private List<Long> excluedeCommonPosts(List<UserInfoBo> oneUserPosts, List<UserInfoBo> usersPostsToExclude) {
		return oneUserPosts
				.stream()
				.filter(x -> usersPostsToExclude.stream().anyMatch(y -> y.getPostId().equals(x.getPostId())))
				.map(UserInfoBo::getPostId)
				.collect(Collectors.toList());
	}

	private double calculateSignatureEntry(Map.Entry<Long, double[]> entry, boolean[] askedUserSignature, SuperBit sb) {
		double[] curUserVector = entry.getValue();
		boolean[] curUserSsignature = sb.signature(curUserVector);
		return sb.similarity(askedUserSignature, curUserSsignature);
	}

	private Map<Long, double[]> createAllUsersVectors(List<Long> allUsersIds, List<Long> allPostIds, Map<Long, List<UserInfoBo>> allUsersDatabaseVector) {
		Map<Long, double[]> usersVector = new HashMap<>(allUsersIds.size());
		for (Long curUserId : allUsersIds) {
			if (allUsersDatabaseVector.containsKey(curUserId)) {
				double[] userVector = createVector(allUsersDatabaseVector.get(curUserId), allPostIds);
				usersVector.put(curUserId, userVector);
			}
		}
		return usersVector;
	}


	private double[] createVector(List<UserInfoBo> userInfoBo, List<Long> allPostIds) {
		double[] doubles = new double[allPostIds.size()];
		for (UserInfoBo infoBo : userInfoBo) {
			double postVectorValue = calculateEntryPrice(infoBo);
			doubles[Math.toIntExact(infoBo.getPostId())] = postVectorValue;
		}
		return doubles;
	}

	private double calculateEntryPrice(UserInfoBo userInfoBo) {
		long time = new Date().getTime();
//		long timeOfPost = userInfoBo.getDateOfPost().getTime();
		long timeOfPost = new Date().getTime()+1;

		long dateEffect = 1 / (time - timeOfPost);
		return userInfoBo.getNumberOfComments()
				+ userInfoBo.getNumberOfLikes()
				+ userInfoBo.getIsUserAndPosterFriends() * 5
				+ dateEffect;
	}
}

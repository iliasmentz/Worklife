export const PARTICLE_GROUND_CONFIG = {
  minSpeedX: 0.1,
  maxSpeedX: 0.7,
  minSpeedY: 0.1,
  maxSpeedY: 0.7,
  directionX: 'center', // 'center', 'left' or 'right'. 'center' = dots bounce off edges
  directionY: 'center', // 'center', 'up' or 'down'. 'center' = dots bounce off edges
  density: 10000, // How many particles will be generated: one particle every n pixels
  dotColor: '#CCE2EE',
  lineColor: '#CCE2EE',
  particleRadius: 7, // Dot size
  lineWidth: 1,
  curvedLines: true,
  proximity: 100, // How close two dots need to be before they join
  parallax: false
};

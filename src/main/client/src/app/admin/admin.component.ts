import {Component, OnInit} from '@angular/core';
import {User} from "../shared/user/user.model";
import {ActivatedRoute} from "@angular/router";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  dataSource: MatTableDataSource<User>;
  displayedColumns = ['userId', 'name', 'surname', 'username', 'birthdate', 'email', 'phoneNumber',
    'dateCreated', 'address'];

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.data.subscribe(resolvedData => {
      this.dataSource = new MatTableDataSource(resolvedData['users']);
    })
  }

}

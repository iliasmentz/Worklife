import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../shared/user/user.model";
import {ActivatedRoute} from "@angular/router";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  dataSource: MatTableDataSource<User>;
  displayedColumns = ['userId', 'name', 'surname', 'username', 'birthdate', 'email', 'phoneNumber',
    'dateCreated', 'address', 'profile', 'friend'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.data.subscribe(resolvedData => {
      this.dataSource = new MatTableDataSource(resolvedData['users']);
    });
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}

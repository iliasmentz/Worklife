import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
  console.log("\n" +
    "__      __             __   .__  .__  _____       \n" +
    "/  \\    /  \\___________|  | _|  | |__|/ ____\\____  \n" +
    "\\   \\/\\/   /  _ \\_  __ \\  |/ /  | |  \\   __\\/ __ \\ \n" +
    " \\        (  (_) )  | \\/    <|  |_|  ||  | \\  ___/ \n" +
    "  \\__/\\  / \\____/|__|  |__|_ \\____/__||__|  \\___  >\n" +
    "       \\/                   \\/                  \\/ ")

  }


}

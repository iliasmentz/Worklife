import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {PARTICLE_GROUND_CONFIG} from "./particle-ground.config";
import Particle from 'particleground-light/dist/index.js';


@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit, AfterViewInit{
  @ViewChild('particles') particles: ElementRef;

  constructor() { }

  ngOnInit() {
    new Particle(this.particles.nativeElement, PARTICLE_GROUND_CONFIG);

  }

  ngAfterViewInit(){
    // libraryVar.getScript('');
  }
}

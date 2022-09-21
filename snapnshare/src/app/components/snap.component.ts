import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebcamImage } from 'ngx-webcam';
import { Subject } from 'rxjs';
import { CameraService } from '../camera.service';

@Component({
  selector: 'app-snap',
  templateUrl: './snap.component.html',
  styleUrls: ['./snap.component.css']
})
export class SnapComponent implements OnInit {

  trigger = new Subject<void>()
  triggerObs = this.trigger.asObservable()

  width = 500

  constructor(private router: Router, private cameraSvc: CameraService) { }

  ngOnInit(): void {
    this.width = Math.floor(window.innerWidth * .9)
  }

  snapshot(img: WebcamImage) {
    this.cameraSvc.dataUrl = img.imageAsDataUrl
    this.router.navigate(['/share'])
  }

  takePicture() {
    this.trigger.next()
  }

}

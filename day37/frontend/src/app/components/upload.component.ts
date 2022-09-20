import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CameraService } from '../services/camera.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  imageSrc!: string
  form!: FormGroup

  constructor(private cameraSvc: CameraService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    if (!this.cameraSvc.image) {
      this.router.navigate(['/'])
      return
    }
    this.imageSrc = this.cameraSvc.image
    this.form = this.fb.group({
      title: this.fb.control('', [Validators.required])
    })
  }

  post() {
    const title = this.form.get('title')?.value
    console.info('>>> title: ', title)
    this.cameraSvc.upload(title)
      .then(result => {
        console.info('>>> result: ', result)
        this.router.navigate(['/'])
      }).catch((error: HttpErrorResponse) => {
        console.error('>>>> error: ', error)
      })
  }

}

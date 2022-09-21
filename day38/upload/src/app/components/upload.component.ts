import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UploadService } from '../services/upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  @ViewChild('toUpload')
  toUpload!: ElementRef

  form!: FormGroup

  constructor(private fb: FormBuilder, private uploadSvc: UploadService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      title: this.fb.control<string>('', [ Validators.required ]),
      file: this.fb.control<any>('', [ Validators.required ]),
    })
  }

  doUpload() {
    console.info('>>> upload: ', this.form.value)
    // @ts-ignore
    console.info('>>> toUpload: ', this.toUpload.nativeElement.files[0])

    const myFile = this.toUpload.nativeElement.files[0]
    const title = this.form.get('title')?.value

    this.uploadSvc.upload(title, myFile)
      .then(result => {
        console.info('>>> result: ', result)
      }) .catch(error => {
        console.error('>> error: ', error)
      })
  }
}

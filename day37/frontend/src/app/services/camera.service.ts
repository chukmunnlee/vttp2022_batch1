import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";

@Injectable()
export class CameraService {

  private _img: string = ''
  set image(img: string) {
    this._img = img
  }
  get image(): string {
    return this._img
  }

  constructor(private http: HttpClient) { }

  upload(title: string) {
    const blob = this.dataURIToBlob(this._img)
    console.info('>>> blob: ', blob)

    const formData = new FormData()
    formData.set('title', title)
    formData.set('myfile', blob, 'pic.png')

    return firstValueFrom(
      this.http.post<any>('/upload', formData)
    )
  }

  dataURIToBlob(dataURI: string) {
    // convert base64 to raw binary data held in a string
    // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
    var byteString = atob(dataURI.split(',')[1]);

    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]

    // write the bytes of the string to an ArrayBuffer
    var ab = new ArrayBuffer(byteString.length);

    // create a view into the buffer
    var ia = new Uint8Array(ab);

    // set the bytes of the buffer to the correct values
    for (var i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    // write the ArrayBuffer to a blob, and you're done
    var blob = new Blob([ab], { type: mimeString });
    return blob;
  }

}

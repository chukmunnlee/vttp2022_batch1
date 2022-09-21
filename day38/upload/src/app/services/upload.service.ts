import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";

@Injectable()
export class UploadService {

  constructor(private http: HttpClient) { }

  upload(title: string, file: File | Blob) {
    const data = new FormData()
    data.set('title', title)
    data.set('myfile', file)
    return firstValueFrom(
      this.http.post<any>('/upload', data)
    )
  }
}

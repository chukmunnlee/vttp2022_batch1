import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms'
import { WebcamComponent, WebcamModule } from 'ngx-webcam'

import { AppComponent } from './app.component';
import { CameraComponent } from './components/camera.component';
import { UploadComponent } from './components/upload.component';
import { CameraService } from './services/camera.service';

const appPath: Routes = [
  { path: '', component: CameraComponent },
  { path: 'upload', component: UploadComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    CameraComponent, UploadComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appPath),
    WebcamModule
  ],
  providers: [ CameraService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

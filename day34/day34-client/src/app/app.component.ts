import { Component } from '@angular/core';
import { Registration } from './models';
import { RegistrationService } from './services/registration.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private registrationSvc: RegistrationService) { }

  processNewRegistration(newRegistration: Registration) {
    console.info('>>>> new registration: ', newRegistration)
    this.registrationSvc.newRegistration(newRegistration)
      .then(result => {
        console.info('>>>> result: ', result)
      })
      .catch(error => {
        console.error('>>>> error: ', error)
      })
  }
}

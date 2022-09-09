import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Registration } from '../models';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Output()
  onNewRegistration = new Subject<Registration>()

  form!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  processRegistration() {
    const registration = this.form.value as Registration
    console.info('>>>> registration: ', registration)
    this.onNewRegistration.next(registration)
    this.form = this.createForm()
  }

  private createForm() {
    return this.fb.group({
      name: this.fb.control('', [ Validators.required ]),
      email: this.fb.control('', [ Validators.required, Validators.email ]),
    })
  }

}

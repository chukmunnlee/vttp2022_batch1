import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Register} from '../models';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

	form!: FormGroup

  regis: Register[] = []

  constructor(private fb: FormBuilder, private regSvc: RegistrationService) { }

  ngOnInit(): void {
	  this.form = this.createForm()
    this.regSvc.getAll()
      .then(result => this.regis = result)
  }

	createForm(): FormGroup {
		return this.fb.group({
			name: this.fb.control<string>(''),
			email: this.fb.control<string>(''),
		})
	}

	processForm() {
		const reg: Register = this.form.value as Register
    this.regSvc.save(reg)
      .then(result => {
        console.info('>>> after put: ', result)
      })
		console.info('>>> reg: ', reg)
    this.form = this.createForm();
	}

}

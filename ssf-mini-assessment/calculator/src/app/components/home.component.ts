import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {CalculateService} from '../calculate.service';
import {Operation} from '../models';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	form!: FormGroup

	constructor(private fb: FormBuilder, private calculateSvc: CalculateService
			, private router: Router) { }

  ngOnInit(): void {
	  this.form = this.createForm();
  }

	process() {
		const oper = this.form.value as Operation;
		this.calculateSvc.operation = oper;
		this.router.navigate(['/result'])
	}

	private createForm(): FormGroup {
		return this.fb.group({
			oper1: this.fb.control('', [ Validators.required ]),
			oper2: this.fb.control('', [ Validators.required ]),
			ops: this.fb.control('', [ Validators.required ])
		});
	}

}

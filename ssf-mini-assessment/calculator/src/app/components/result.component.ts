import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CalculateService} from '../calculate.service';
import {OperationResult} from '../models';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

	result = "assets/wrong.png"

	constructor(private calculateSvc: CalculateService) { }

	ngOnInit(): void { 
		this.result = 'assets/wrong.png'
		this.calculateSvc.perform()
			.then(resp => {
				if (this.checkResult(resp))
					this.result = 'assets/correct.png'
			})
			.catch(error => {
				alert('Error: ' + JSON.stringify(error))
			})
	}

	checkResult(resp: OperationResult) {
		console.info('>>>> result = ', Math.floor(this.calculateSvc.getResult()))
		console.info(">>> result ", Math.floor(resp.result))
		return ('result' in resp) && ('timestamp' in resp) && ('userAgent' in resp)
			&& (typeof resp.result == 'number') 
			&& (typeof resp.timestamp == 'string') && (null != resp.timestamp)
			&& (typeof resp.userAgent == 'string') && (null != resp.userAgent)
			&& Math.floor(this.calculateSvc.getResult()) == Math.floor(resp.result)
	}

}

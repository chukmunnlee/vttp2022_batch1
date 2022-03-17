import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {firstValueFrom} from "rxjs";
import {Operation, OperationResult} from "./models";

@Injectable()
export class CalculateService {

	operation: Operation = { oper1: 0, oper2: 0, ops: 'plus' }

	constructor(private http: HttpClient) { }

	perform(): Promise<OperationResult> {
		if (null != this.operation)
			return firstValueFrom(
				this.http.post<OperationResult>('/calculate', this.operation))

		return Promise.reject();
	}

	getResult() {
		switch (this.operation.ops) {
			case 'plus':
				return this.operation.oper1 + this.operation.oper2
			case 'minus':
				return this.operation.oper1 - this.operation.oper2
			case 'divide':
				return this.operation.oper1 / this.operation.oper2
			case 'multiply':
				return this.operation.oper1 * this.operation.oper2
			default:
				return NaN
		}
	}

}

import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {min} from 'rxjs';
import {SearchCriteria} from '../models';
import { GiphyService } from '../services/giphy.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

	searchForm!: FormGroup

	constructor(private fb: FormBuilder, private giphySvc: GiphyService) { }

	ngOnInit(): void {
		this.searchForm = this.createForm()
	}

	performSearch() {
		const criteria: SearchCriteria = this.searchForm.value as SearchCriteria
		console.info('search criteria: ', criteria)
    this.giphySvc.search(criteria)
      .then(result => {
        console.info('>>>> result: ', result)
        // Only save if the call is successful
        this.saveAPIKey(criteria.apiKey)
        this.searchForm = this.createForm()
        this.giphySvc.onNewResult.next(result)
      })
      .catch(error => {
        console.error('>>>> error: ', error)
      })
	}

	private createForm(): FormGroup {
		return this.fb.group({
			apiKey: this.fb.control<string>(this.getAPIKey(), [ Validators.required ]),
			search: this.fb.control<string>('', [ Validators.required ]),
			results: this.fb.control<number>(10, [ Validators.min(5) ]),
			rating: this.fb.control<string>('g')
		})
	}

  private getAPIKey(): string {
    let key = localStorage.getItem('apiKey')
    if (!key)
      return ''
    return key
  }
  private saveAPIKey(key: string) {
    localStorage.setItem('apiKey', key)

  }

}

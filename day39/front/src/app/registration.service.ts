import { Injectable } from "@angular/core";

import Dexie from 'dexie'

import { Register} from './models'

@Injectable()
export class RegistrationService extends Dexie {

  reg!: Dexie.Table<Register, string>

  constructor() {
    super('myregistrations')
    // Create tables
    this.version(1).stores({
      registration: 'email'
    })

    this.reg = this.table('registration')
  }

  save(r: Register) {
    return this.reg.put(r)
  }

  getAll() {
    // Promise Register[]
    return this.reg.toArray()
  }

}

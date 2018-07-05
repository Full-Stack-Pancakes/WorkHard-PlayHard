import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor(private httpClient: HttpClient) { }
  beanurl = "http://project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/users";
  beanurl2 = "http://Project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/events";
  getUsers(): Promise<any>{
    return this.httpClient.get(`http://project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/users`).toPromise();
  }
  getUserById(id: number): Promise<any>{
    return this.httpClient.get(`http://project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/users/` + id).toPromise();
  }
  getUserByEmail(email: string): Promise<any>{
    return this.httpClient.get(`http://project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/users/` + email).toPromise();
  }
  createUser(json: JSON): Promise<any>{
    return this.httpClient.post(`http://project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/users`, json).toPromise();
  }
  updateUser(json: JSON): Promise<any>{
    return this.httpClient.put(`http://Project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/users`, json).toPromise();
  }
  deleteUserById(id: number): Promise<any>{
    return this.httpClient.delete(this.beanurl + id).toPromise();
  }
  getEventById(id: number): Promise<any>{
    return this.httpClient.get(`http://project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/events/` + id).toPromise();
  }
  createEvent(json: JSON): Promise<any>{
    return this.httpClient.post(`http://Project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/events`, json).toPromise();
  }
  updateEvent(json: JSON): Promise<any>{
    return this.httpClient.put(`http://Project2-env.yw7euukwbt.us-east-2.elasticbeanstalk.com/events`, json).toPromise();
  }
  deleteEventById(id: number): Promise<any>{
    return this.httpClient.delete(this.beanurl2 + id).toPromise();
  }
}

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  // private url = 'http://backend:9229';
  private url = 'http://feed-oop.ddns.net:9229';

  constructor(private http: HttpClient) {}

  hasUser(uid: string) {
    return this.http.get(this.url + '/users');
  }

  addUser(uid: string, imgUrl: string) {
    const headers = {
      'content-type': 'application/json',
    };
    const body = JSON.stringify({ uid, imgUrl });
    return this.http.post(this.url + '/users', body, { headers });
  }

  addPost(uid: string, content: string) {
    const headers = {
      'content-type': 'application/json',
    };
    const params = new HttpParams().set('userId', uid);
    const body = JSON.stringify({ content });
    return this.http.post(this.url + '/posts', body, { headers, params });
  }

  getPosts() {
    return this.http.get(this.url + '/posts');
  }
}

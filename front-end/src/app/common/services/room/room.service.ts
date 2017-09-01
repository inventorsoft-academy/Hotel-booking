import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import {Room} from '../../models/room';
import {environment} from "../../../../environments/environment";

@Injectable()
export class RoomService {

  constructor(private http: Http) {
  }

  API = 'http://localhost:8080/rooms/';

  updateRoom(id, obj: {}): Observable<any> {
    const url = `${environment.roomApi}${id}`;
    return this.http.post(url, obj).map(res => res.json());
  }

  getRooms(): Observable<Room[]> {
    const url = `${environment.roomApi}`;
    return this.http.get(url).map(res => res.json());
  }

  saveRoom(obj: {}): Observable<Response> {
    const url = `${environment.roomApi}`;
    return this.http.post(url, obj);
  }

  findRoomById(id): Observable<Room> {
    const url = `${environment.roomApi}${id}`;
    return this.http.get(url)
      .map(res => res.json());
  }


  deleteRoomById(id): Observable<any> {
    const url = `${environment.roomApi}${id}`;
    return this.http.delete(url);
  }

  getRoomTypes():Observable<any> {
    const url = `${environment.roomApi}roomTypes`;
    console.log(url);
    return this.http.get(url).map(res => res.json());
  }
}

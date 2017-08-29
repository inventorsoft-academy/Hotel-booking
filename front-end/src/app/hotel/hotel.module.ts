import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {RoomListComponent} from './room-list/room-list.component';
import { RoomAddComponent } from './room-add/room-add.component';
import {HotelComponent} from "./hotel.component";
import { HeaderComponent } from './header/header.component';
import { RoomDetailsComponent } from './room-details/room-details.component';
import { RoomUpdateComponent } from './room-update/room-update.component';
import {MaterialModule} from "../material.module";

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    RouterModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  declarations: [
    HotelComponent,
    HeaderComponent,
    RoomListComponent,
    RoomAddComponent,
    RoomDetailsComponent,
    RoomUpdateComponent,

  ]
})
export class HotelModule { }

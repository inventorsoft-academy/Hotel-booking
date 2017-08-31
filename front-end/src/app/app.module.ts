import { NgModule } from '@angular/core';
import 'hammerjs';
import {AppComponent} from './app.component';
import {RoomService} from './common/services/room/room.service';
import {RoomListComponent} from './hotel/room-list/room-list.component';
import { HotelComponent } from './hotel/hotel.component';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {HotelModule} from "./hotel/hotel.module";
import {RoomAddComponent} from "./hotel/room-add/room-add.component";
import {RoomDetailsComponent} from "./hotel/room-details/room-details.component";
import {RoomUpdateComponent} from "./hotel/room-update/room-update.component";
import {FlexLayoutModule} from "@angular/flex-layout";

const routes = [

  {
    path: 'hotel',
    component: HotelComponent,
    children: [
      {
        path: 'rooms',
        component: RoomListComponent
      },
      {
        path: 'room-add',
        component: RoomAddComponent
      },
      {
        path: 'room-details/:id',
        component: RoomDetailsComponent
      },
      {
        path: 'room-update/:id',
        component: RoomUpdateComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: 'hotel',
    pathMatch: 'full'
  }

];

@NgModule({
  imports: [
    BrowserModule,
    HotelModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [
    AppComponent
  ],
  providers: [RoomService],
  bootstrap: [AppComponent]
})

export class AppModule { }

import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';
import {Room} from '../../common/models/room';
import {RoomService} from '../../common/services/room/room.service';

@Component({
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit, OnDestroy {

  roomsList: Room[];

  subscriptions: Subscription[] = [];
  constructor(private roomService: RoomService,
              private router: Router) { }

  ngOnInit() {
    this.getRooms();
  }

  getRooms() {
    const getRoomsSub = this.roomService.getRooms()
      .subscribe(
        res => {
          this.roomsList = res;
        }
      );
    this.subscriptions.push(getRoomsSub);
  }

  findRoomById(id) {
    this.router.navigate([`hotel/room-details/${id}`]);
  }

  deleteRoomById(id) {
    const deleteRoomSub = this.roomService.deleteRoomById(id)
      .subscribe(res => {
        this.getRooms();
      });
    this.subscriptions.push(deleteRoomSub);
  }

  updateRoomGet(id) {
    this.router.navigate([`hotel/room-update/${id}`]);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }


}

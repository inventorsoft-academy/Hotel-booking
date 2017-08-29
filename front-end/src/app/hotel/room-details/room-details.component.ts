import {Component, OnDestroy, OnInit} from '@angular/core';
import {Room} from "../../common/models/room";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";
import {RoomService} from "../../common/services/room/room.service";

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit, OnDestroy {
  room: Room;

  subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private roomService: RoomService) {}

  ngOnInit() {
    this.findRoomById(this.route.snapshot.params.id);
  }

  findRoomById(id) {
    let findRoomSubscription = this.roomService.findRoomById(id)
      .subscribe(
        res => {
          this.room =res;
        }
      );
    this.subscriptions.push(findRoomSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    )
  }

}

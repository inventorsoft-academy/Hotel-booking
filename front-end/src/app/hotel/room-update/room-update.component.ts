import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {RoomService} from "../../common/services/room/room.service";
import {Room} from "../../common/models/room";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  templateUrl: './room-update.component.html',
  styleUrls: ['./room-update.component.css']
})
export class RoomUpdateComponent implements OnInit, OnDestroy {

  room: Room;

  oldRoom: Room;

  roomTypeList: any[];

  subscriptions: Subscription[] = [];

  updateRoomForm = this.fb.group({
    type: ['', Validators.required],
    price: ['', Validators.required],
    available: ['', Validators.required]
  });

  constructor(private fb: FormBuilder,
              private roomService: RoomService,
              private route: ActivatedRoute,
              private  router: Router) {
    this.findRoomById(this.route.snapshot.params.id)
  }

  ngOnInit() {
    this.getRoomTypes();
    this.roomService.findRoomById(this.route.snapshot.params.id).subscribe(res => this.room = res);
  }

  updateRoom() {
    this.roomService.updateRoom(this.route.snapshot.params.id, this.updateRoomForm.value).subscribe();
  }

  getRoomTypes() {
    const getRoomsSubscription = this.roomService.getRoomTypes()
      .subscribe(
        res => {
          this.roomTypeList = res;
        }
      );
    this.subscriptions.push(getRoomsSubscription);
  }

  findRoomById(id) {
    let findRoomSubscription = this.roomService.findRoomById(id)
      .subscribe(
        res => {
          this.oldRoom =res;
        }
      );
    this.subscriptions.push(findRoomSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}

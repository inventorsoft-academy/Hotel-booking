import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {RoomService} from "../../common/services/room/room.service";
import {Room} from "../../common/models/room";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";

@Component({


  selector: 'app-room-update',
  templateUrl: './room-update.component.html',
  styleUrls: ['./room-update.component.css']
})
export class RoomUpdateComponent implements OnInit, OnDestroy {

  room: Room;

  roomTypeList: any[];

  subscriptions: Subscription[] = [];

  updateRoomForm = this.fb.group({
    type: ['', Validators.required],
    price: ['', Validators.required],
    available: ['', Validators.required]
  });

  constructor(private fb: FormBuilder,
              private roomService: RoomService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getRoomTypes();
    this.roomService.findRoomById(this.route.snapshot.params.id).subscribe(res => this.room = res);
  }

  updateRoom() {
    this.roomService.updateRoom(this.route.snapshot.params.id, this.updateRoomForm.value).subscribe(res => console.log(res));
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

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}

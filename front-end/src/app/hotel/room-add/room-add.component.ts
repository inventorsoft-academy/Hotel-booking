import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {FormBuilder, Validators} from "@angular/forms";
import {RoomService} from "../../common/services/room/room.service";
import {Room} from "../../common/models/room";
import {MdSnackBar} from '@angular/material';

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrls: ['./room-add.component.css']
})
export class RoomAddComponent implements OnInit, OnDestroy {

  room: Room;

  available = true;

  newRoomForm = this.fb.group({
    type: ['', Validators.required],
    price: ['', [Validators.required, Validators.min(5), Validators.minLength(5)]],
    available: ['', Validators.required]
  });

  subscriptions: Subscription[] = [];

  roomTypeList: any[];

  constructor(private fb: FormBuilder,
              private roomService: RoomService,
              private snackBar: MdSnackBar) { }

  roomSavedPopup(message: string) {
    this.snackBar.open(message, '', {
      duration: 2000,


    });
  }


  ngOnInit() {
    this.getRoomTypes();
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
  saveRoom() {
    let saveRoomSubscription = this.roomService.saveRoom(this.newRoomForm.value).subscribe(
      res => this.success(),
      res => this.failure());
    this.subscriptions.push(saveRoomSubscription);
  }

  clearForm() {
    this.newRoomForm.reset();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }

  private success() {
    this.roomSavedPopup("Room saved");
    this.clearForm();
  }

  private failure() {
    this.roomSavedPopup("Error");
  }
}

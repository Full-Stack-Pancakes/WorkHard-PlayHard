import { Time } from "@angular/common";
import { EventColor, EventAction } from "calendar-utils";

export class Event {
    // location: string;
    // description: string;
    // priority: Number;
    // eventtype: string;
    // inputtime: Date;
    // start: Date;
    // end: Date;
    // eventlength: Time;
    // splitable: Boolean;
    // minlength: Time;
    // dayofweek: string;
    // timezone: string;
    // userid: Number;
    id: number;
    start: Date;
    end: Date;
    title: string;
    color: {'#ad2121', '#FAE3E3'};
    actions: EventAction[];
    allDay: boolean;
    inputtime: Date;
    
    eventlength: Time;
    splitable: Boolean;
    minlength: Time;
    userid: Number;
}
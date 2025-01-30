@startuml Parkinglot 
skinparam ClassAttributeIconSize 0
skinparam TitleFontSize 20
title Parkinglot UML Diagram
!theme spacelab

class ParkingLot {

	- id : int
	- status : ParkingLotStatus
	- floors : Floor
	- entryGates : Gate
	- exitGates : Gate
	- supportedVehicleTypes : VehicleType
	- feeCalculationStrategy : FeeCalculationStrategy
	- slotAllocationStrategy : SlotAllocationStrategy
	{method} + allocateSlot(vehicleDetails : vehicleDetails) : Slot
	{method} + calculateFee(vehicleDetails : VehicleDetails) : Ticket
	{method} + performTransaction(ticket : Ticket) : PaymentTransaction
}

class Floor {
	
	- id : int
	- number : int
	- slots : Slot
	- floorType : FloorType
	- status : FloorStatus

}

class Slot {

	- id : int
	- vehicleType : VehicleType
	- status : SlotStatus
	- vehicle : Vehicle

}

class Gate {

	- id : int
	- gateType : GateType
	- status : GateStatus
	- operator : Operator
	- gateNumber : String

}

class Vehicle {

	- id : int
	- vehicleType : VehicleType
	- number : String
	
}

class VehicleDetails {

	- id : int
	- vehicle : Vehicle
	- entryTime : LocalDateTime
	- exitTime : LocalDateTime
	- operator : Operator

}

class Operator {
class VehicleDetails {

    - id : int
    - vehicle : Vehicle
    - entryTime : LocalDateTime
    - exitTime : LocalDateTime
    - operator : Operator

}

class Operator {

    - id : int
    - name : String

}

class Ticket {
    
    - id : int 
    - vehicleName: String
    - entryTime : LocalDateTime
    - exitTime : LocalDateTime
    - billAmount : int

}

class PaymentTransaction {

    - id : int
    - ticket : Ticket
    - transactionStatus : TransactionStatus

}

interface PaymentTransactionStrategy {

	- id : int
	- name : String

}

class Ticket {
	
	-id : int 
	- vehicleName: String
	- entryTime : LocalDateTime
	- exitTime : LocalDateTime
	- billAmount : int

}

class PaymentTransaction {

	- id : int
	- ticket : Ticket
	- transactionStatus : TransactionStatus

}

interface PaymentTransactionStrategy {

	{abstract} + performTransaction(ticket : Ticket) : PaymentTransaction

}

interface FeeCalculationStrategy {

	{abstract} + calculateFee(vehicleDetails : VehicleDetails) : Ticket
}	


interface SlotAllocationStrategy {

	{abstract} + allocateSlot(vehicleDetails : VehicleDetails) : Slot

}

enum TransactionStatus {
	COMPLETED
	NOTCOMPLETED
}

enum GateStatus {
	OPERATIONAL
	MAINTAINANCE
}


enum VehicleType {

	TWOWHEELER
	FOURWHEELER

}

enum ParkingLotStatus {

	MAINTAINANCE
	OPERATIONAL
	FULL

}

enum FloorType {

	BIKE
	CAR
	MIXED

}

enum FloorStatus {

	MAINTAINANCE
	FULL
	OPERATIONAL

}

enum SlotStatus {
	OPERATIONAL
	OCCUPIED
	MAINTANANCE
}

ParkingLot "1" --* "*" Floor
ParkingLot "1" --* "*" Gate
ParkingLot --* PaymentTransactionStrategy
ParkingLot --* FeeCalculationStrategy
ParkingLot --* SlotAllocationStrategy

Floor "1" --* "*" Slot
Slot --o Vehicle

Gate --- Operator

VehicleDetails --* Vehicle
Ticket --* PaymentTransaction

FeeCalculationStrategy --- Ticket
PaymentTransactionStrategy --- PaymentTransaction

@enduml
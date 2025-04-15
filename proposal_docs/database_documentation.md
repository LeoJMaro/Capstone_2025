# Database Documentation: Taylor Insurance

## Overview

The insurance database is designed to manage customer information, policies, vehicles, residences, quotes, transactions, and accidents. It supports both home and auto insurance policies and enforces business rules such as policy limits, premium calculations, and automatic renewals.


## Tables


1. Users
Stores information about users, including customers, admins, and agents.

Column Name	Data Type	        Constraints			            Description

UserID		    INT		        PRIMARY KEY, AUTO_INCREMENT	    Unique identifier for each user.

FirstName	    VARCHAR(50)	    NOT NULL			            User's first name.
LastName	    VARCHAR(50)	    NOT NULL			            User's last name.
Email		    VARCHAR(100)	UNIQUE, NOT NULL		        User's email address.
PasswordHash	VARCHAR(255)	NOT NULL			            Hashed password for security
Role		    ENUM		    NOT NULL			            User role (Admin, Customer, Agent).
Phone		    VARCHAR(15)	    NOT NULL, CHECK			        User's phone number (must start with 709-).
Address		    VARCHAR(255)	NOT NULL			            User's address.
DateOfBirth	    DATE		    NOT NULL			            User's date of birth.
DriverLicense	VARCHAR(20)	    UNIQUE, NOT NULL		        User's driver's license number
CreatedAt	    TIMESTAMP	    DEFAULT CURRENT_TIMESTAMP	    Timestamp of user creation.

Relationships :
Linked to Policies (1:N) via UserID.
Linked to Vehicle (1:N) via UserID.
Linked to Residences (1:N) via UserID.


2. Policies
Stores insurance policy information.

Column Name		Data Type	        Constraints			            Description

PolicyID		INT		            PRIMARY KEY, AUTO_INCREMENT	    Unique identifier for each policy.
UserID			INT		            FOREIGN KEY (Users)		        Links policy to a user.
PolicyType		ENUM		        NOT NULL			            Type of policy (Home, Auto).
StartDate		DATE		        NOT NULL			            Start date of the policy.
EndDate			DATE		        NOT NULL			            End date of the policy.
BasePremium		DECIMAL(10, 2)	    NOT NULL			            Base premium amount.
Tax			    DECIMAL(10, 2)	    NOT NULL			            Tax amount (15% HST).
TotalPremium	DECIMAL(10, 2)	    NOT NULL			            Total premium (BasePremium + Tax).
Status			ENUM		        NOT NULL			            Status of the policy (Active, Expired, Cancelled).

Relationships :
Linked to AutoPolicies (1:1) via PolicyID.
Linked to HomePolicies (1:1) via PolicyID.
Linked to Accidents (1:N) via PolicyID.

3. Vehicle
Stores information about vehicles.

Column Name	Data Type	Constraints			        Description

VehicleID	INT		    PRIMARY KEY, AUTO_INCREMENT	    Unique identifier for each vehicle.
UserID		INT		    FOREIGN KEY (Users)		        Links vehicle to a user.
Make		VARCHAR(50)	NOT NULL			        Vehicle make (e.g., Toyota, Ford).
Model		VARCHAR(50)	NOT NULL			        Vehicle model (e.g., Camry, F-150).
Year		INT		    NOT NULL			            Vehicle year (e.g., 2020).
CreatedAt	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP	Timestamp of vehicle creation.

Relationships: Linked to AutoPolicies (1:N) via VehicleID.


4. AutoPolicies
Stores details specific to auto policies.

Column Name		    Data Type	Constraints			            Description

AutoPolicyID	    INT		    PRIMARY KEY, AUTO_INCREMENT	    Unique identifier for each auto policy.
PolicyID		    INT		    FOREIGN KEY (Policies)		    Links to the parent policy.
DriverAge		    INT		    NOT NULL			            Age of the driver.
AccidentsLast5Years	INT		    NOT NULL			            Number of accidents in the last 5 years.
VehicleID		    INT		    FOREIGN KEY (Vehicle)		    Links to the vehicle.

Relationships: Directly tied to Policies and Vehicle.


5. Residences
Stores information about residences.

Column Name	    Data Type	    Constraints			            Description

ResidenceID	    INT		        PRIMARY KEY, AUTO_INCREMENT	    Unique identifier for each residence.
UserID		    INT		        FOREIGN KEY (Users)		        Links residence to a user.
AgeBuilt	    INT		        NOT NULL			            Age of the residence.
DwellingType	VARCHAR(50)	    NOT NULL			            Type of dwelling (e.g., standalone, bungalow).
HeatingType	    VARCHAR(50)	    NOT NULL			            Type of heating (e.g., oil, electric).
Location	    ENUM		    NOT NULL			            Location (Urban, Rural).
HomeValue	    DECIMAL(10, 2)	NOT NULL			            Value of the residence.

Relationships: Linked to HomePolicies (1:N) via ResidenceID.


6. HomePolicies
Stores details specific to home policies.

Column Name	        Data Type	    Constraints			        Description

HomePolicyID	    INT		        PRIMARY KEY, AUTO_INCREMENT	Unique identifier for each home policy.
PolicyID	        INT		        FOREIGN KEY (Policies)		Links to the parent policy.
ResidenceID	        INT		        FOREIGN KEY (Residences)	Links to the residence.
LiabilityLimit	    DECIMAL(10, 2)	NOT NULL			        Liability limit.
Deductible	        DECIMAL(10, 2)	NOT NULL			        Deductible amount.

Relationships : Directly tied to Policies and Residences.

7. Accidents
Stores information about accidents.

Column Name	        Data Type	        Constraints			            Description

AccidentID	        INT		            PRIMARY KEY, AUTO_INCREMENT	    Unique identifier for each accident.
PolicyID	        INT		            FOREIGN KEY (Policies)		    Links to the parent policy.
AccidentDate	    DATE		        NOT NULL			            Date of the accident.
AtFaultDriver	    VARCHAR(100)	    NOT NULL			            Name of the at-fault driver.


## Relationships

Users → Policies: One-to-Many (One user can have multiple policies).

Policies → AutoPolicies: One-to-One (One policy can have one auto policy).

Policies → HomePolicies: One-to-One (One policy can have one home policy).

Users → Vehicle: One-to-Many (One user can have multiple vehicles).

Users → Residences: One-to-Many (One user can have multiple residences).

Policies → Accidents: One-to-Many (One policy can have multiple accidents).



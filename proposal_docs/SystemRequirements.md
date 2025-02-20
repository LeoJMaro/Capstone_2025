**System Requirements** 

**Version 1.0**

*2025*

**Context**

Taylor Insurance is an insurance brokerage that provides excellent service to its customers.  With well-located offices throughout the province and easily accessible insurance representatives, customer service is Taylor’s priority.  

To better serve the modern customers Taylor Insurance is planning to expand its online customer servicing.  Taylor Insurance is looking for interested IT consultancy companies to enter a bidding process to help move the company’s business development and quoting and renewal processes online.

**Non-Functional Requirements**

**Operational**

- There will a main customer and staff website (“Website”) and one REST API controller (“API”). The Website and REST API constitute the system (“System”)
- New Customers should be able to create a profile and quote a policy via the Website 
- Existing Customers should be able to purchase, renew, cancel and view policies via the Website after they login.
- The Website must be mobile friendly.

**Performance**

- The system will run smoothly and efficiently with very little wait times for customers
- The system can handle 15,000 customers with room for at least 50% growth smoothly

**Security**

- All customer data will be securely stored on a centralized server
- Usernames and passwords must be secure

**Cultural** 

- The system shall be built so that transition to French language will be seamless (in the future)

**Design**

- Website should be minimalist and modern with an updated colour scheme

**Functional Requirements**

- **Platform requirements:**
  - Website must can be built in Java or a JavaScript framework
  - The database must be relational and SQL compliant 
  - All branding, logo and color schemes will be provided as part of the process
- Core functionality:
  - Website must allow the customer to do the following:
    - Login / register new user
    - edit user profile
    - Quote a policy (new or existing customers)
    - Renew a policy (You can actively renew or cancel within 1 months of expiry. No action means auto renew.)
    - Cancel a policy
    - Contact a service rep (web form and clickable number)
    - Admin site for admin users
      - Any of the policy actions for a customer above
      - Reports for active policies and quotes (active and inactive)
        - Reports by policy type (home / auto)
        - Premiums sorted by year
        - *Reports to be determined with client input*
      - Update the rating factors
  - API must be able to do the following:
    - Create new user
    - edit profile
    - Quote a policy (new or existing customers)
    - Renew a policy (within 2 months of expiry)
    - Cancel a policy
    - Handle Report Data Sets *– Reports to be determined with client input.*
- Infrastructure Requirements
  - Customer website can be built in any JavaScript framework (eg. React) or Java with Spring
  - API must be a Spring REST API
  - Hosting the final project on AWS for demonstration would an excellent extra (BONUS)
- Business requirements
  - Policies have the following properties
    - Single insured person
    - Start date and an end date - all policies are 1 year long
    - Base Premium, Tax (15% HST) and Total Premium
    - Either a home or an auto policy
    - Policies will renew automatically after a year. 
      - This happens because a quote is created with 1 month left and the quote will automatically be purchased unless it is declined.
  - Home policies have the following properties
    - A single residence with the following properties (which are risk factors)
      - Age built
      - Type of dwelling (standalone, bungalow, attached, semi-attached, etc.)
      - Heating type
      - Location (urban vs rural)
    - Actual cost value (home value)
    - Liability limit and deductible for people harmed on property 
    - Contents insurance limit and deductible (these are not used in calculations and can just be ignored during quoting)
  - Auto policies have the following properties
    - Insured person is principal driver 
      - Age, number of accidents in last 5 years (risk factors) 
      - Address
    - A vehicle with make/model/year
  - A customer with an active home and auto policy gets 10% off both
    - Note: premiums are not dynamic – they are fixed for a year so if one policy expires the 10% off doesn’t leave the other policy until renewal
  - A customer can have at most one active home policy and two active auto policies.
  - Premium risk rates must be easily updated by retrieving rates from a web service.
  - Accidents have a date, at fault driver (assume there is only one).
    - Assume that only the single insured person on the policy can get in an accident

**Premium Rating (Values are susceptible to change)**

- **Home Insurance Premium**

`		`Base Premium: $500

`		`Home value factor: 0.2% of home value above $250,000

`		`Liability Limit Factor: $1M: 1.0, $2M: 1.25

`		`Home Age Factor: >25 years old: 1.25; >50 years old: 1.5; all others: 1

`		`Heating Factor: oil heat: 2.0; wood heat: 1.25; all other: 1

`		`Location Factor: urban: 1.0; rural: 1.15

`		`Discount factor: 0.9 if active Auto Policy

`		`Premium calculation: Base \* *all factors* \* tax rate

- **Auto Insurance Premium**

`		`Base Premium: $750

`		`Driver Age Factor: <25 years old: 2; otherwise: 1

Accidents: >2 accidents in last 5 yrs: 2.5; 1 accident in last 5 yrs: 1.25; otherwise: 1

Vehicle: Car >10 years old: 2; Car >5 years old: 1.5; otherwise: 1

`		`Discount factor: 0.9 if active Home Policy

Premium calculation: Base \* *all factors* \* tax rate


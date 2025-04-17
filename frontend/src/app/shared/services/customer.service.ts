// for component
// this.customerService.getUserIdByEmail("emily.johnson@email.com").subscribe({
//   next: (response) => {
//     // Handle the response data
//     this.userData = response;
//     console.log("customer id :", this.userData)
//   },
//   error: (err) => {
//     // Handle errors if any
//     console.error('Error fetching quote:', err);
//   }
// });



// for this service

// getUserIdByEmail(email: string) {
//   return this.http.get<any>(`http://localhost:8080/v1/users/id-by-email/${email}`);
// }

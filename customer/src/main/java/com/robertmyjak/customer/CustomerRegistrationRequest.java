package com.robertmyjak.customer;

public record CustomerRegistrationRequest (
      String firstName,
      String lastName,
      String email){
}

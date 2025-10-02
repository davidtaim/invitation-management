# Invitation Management Microservice

## Using Spring Boot

### Endpoints
#### Auth
- [ ] `POST /api/auth/login`
- [ ] `POST /api/auth/logout`
- [x] `POST /api/auth/register`
- [x] `PUT /api/auth/change/password`
- [x] `PUT /api/auth/change/email`

#### Events
- [x] `GET - /api/events`
- [x] `POST - /api/events`
- [x] `DELETE - /api/events/{id}`
- [x] `GET - /api/events/{id}`
- [x] `PUT - /api/events/{id}`
- [x] `GET - /api/events/{id}/attendance`
- [x] `GET - /api/events/{id}/invitations`
- [x] `POST - /api/events/{id}/invitations`
- [x] `GET - /api/events/{id}/seats`
- [x] `POST - /api/events/{id}/seats`
- [x] `POST - /api/events/{id}/seats/{amount}`
- [x] `GET - /api/events/{id}/seats/available`

#### Guests
- [x] `GET - /api/guests`
- [x] `POST - /api/guests`
- [x] `DELETE - /api/guests/{id}`
- [x] `GET - /api/guests/{id}`
- [x] `PUT - /api/guests/{id}`

#### Invitations
- [x] `GET - /api/invitations/{id}`
- [x] `PUT - /api/invitations/{id}`
- [x] `POST - /api/invitations/{id}/guests/{invitationGuestId}/seat`
- [x] `PUT - /api/invitations/{id}/guests/{invitationGuestId}/checkin`
- [x] `PUT - /api/invitations/{id}/guests/{invitationGuestId}/checkout`
- [x] `GET - /api/invitations/{id}/guests/attendance`
- [x] `PUT - /api/invitations/{id}/guests/{invitationGuestId}/status/{status}`
- [x] `GET - /api/invitations/{id}/guests`
- [x] `POST - /api/invitations/{id}/guests`

#### Roles
- [x] `GET /api/roles`
- [x] `POST /api/roles`
- [x] `DELETE /api/roles/{id}`
- [x] `GET /api/roles/{id}`
- [x] `PUT /api/roles/{id}`

#### Seats
- [x] `DELETE /api/seats/{id}`
- [x] `GET /api/seats/{id}`
- [x] `PUT /api/seats/{id}`

#### Seats Category
- [x] `GET /api/seats/category`
- [x] `POST /api/seats/category`
- [x] `DELETE /api/seats/category/{id}`
- [x] `GET /api/seats/category/{id}`
- [x] `PUT /api/seats/category/{id}`

#### Users
- [x] `GET /api/users`
- [x] `DELETE /api/users/{id}`
- [x] `GET /api/users/{id}`
- [x] `PUT /api/users/{id}`

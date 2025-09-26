# Event and Invitation Program Tables
Created: 9/24/2025, 4:10:35 PM
Last Updated: 9/25/2025, 11:45:22 AM
---

### User

Genera mas tablas necesarias para un programa que se use para generar eventos y sus invitaciones independientes


---

### Assistant (gpt-5-chat)

¡Perfecto! 🚀  
Para un **programa que gestiona eventos y sus invitaciones independientes**, podemos pensar en un modelo de base de datos relacional que permita:

1. Crear eventos.  
2. Gestionar invitados (personas).  
3. Enviar invitaciones únicas a cada invitado en cada evento.  
4. Controlar el estatus de la invitación (pendiente, aceptada, rechazada, confirmada).  
5. Opcionalmente controlar sesiones, ubicaciones, organizadores y recordatorios.

Aquí te muestro un **modelo de tablas** que puede cubrir lo esencial y escalar:

---

### **Tablas Principales**

#### 1. `usuarios`
(Para quienes usan la plataforma, como administradores u organizadores)

```sql
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rol ENUM('admin', 'organizador', 'invitado') DEFAULT 'invitado',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

#### 2. `eventos`
(Define cada evento)

```sql
CREATE TABLE eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    id_organizador INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME,
    ubicacion VARCHAR(255),
    estado ENUM('borrador','publicado','cancelado') DEFAULT 'borrador',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_organizador) REFERENCES usuarios(id_usuario)
);
```

---

#### 3. `invitados`
(Registro de personas a las que se puede invitar, aunque no sean “usuarios” registrados)

```sql
CREATE TABLE invitados (
    id_invitado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    empresa VARCHAR(100),
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

#### 4. `invitaciones`
(Una invitación específica de un invitado a un evento)

```sql
CREATE TABLE invitaciones (
    id_invitacion INT AUTO_INCREMENT PRIMARY KEY,
    id_evento INT NOT NULL,
    id_invitado INT NOT NULL,
    codigo_unico VARCHAR(50) UNIQUE NOT NULL,
    estado ENUM('pendiente','aceptada','rechazada','asistio','no_asistio') DEFAULT 'pendiente',
    enviada_en DATETIME,
    respondida_en DATETIME,
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento),
    FOREIGN KEY (id_invitado) REFERENCES invitados(id_invitado)
);
```

---

### **Tablas Opcionales (para ampliar el sistema)**

#### 5. `recordatorios`
(Notificaciones automáticas, por ejemplo vía email o WhatsApp)

```sql
CREATE TABLE recordatorios (
    id_recordatorio INT AUTO_INCREMENT PRIMARY KEY,
    id_invitacion INT NOT NULL,
    tipo ENUM('email','sms','whatsapp'),
    enviado_en DATETIME,
    FOREIGN KEY (id_invitacion) REFERENCES invitaciones(id_invitacion)
);
```

---

#### 6. `sesiones_evento`
(Si un evento tiene varias sesiones o actividades internas)

```sql
CREATE TABLE sesiones_evento (
    id_sesion INT AUTO_INCREMENT PRIMARY KEY,
    id_evento INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    ponente VARCHAR(100),
    hora_inicio DATETIME NOT NULL,
    hora_fin DATETIME,
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento)
);
```

---

### 🔑 Flujo básico del sistema:
1. Un **usuario organizador** crea un **evento**.  
2. Carga o selecciona de la lista de **invitados**.  
3. El sistema genera **invitaciones** con un código único (ejemplo: un enlace tipo QR o URL).  
4. El invitado responde → se actualiza el estado de la invitación.  
5. Opcionalmente se envían **recordatorios**.  

---

👉 Pregunta para afinar:  
¿Quieres que el sistema maneje **asignación de mesas/asientos** (ejemplo: bodas/congresos) o te interesa solo que el invitado confirme su asistencia?


---

### User

Puedes poner las tablas en ingles


---

### Assistant (gpt-5-chat)

¡Claro que sí! 🙌 Aquí tienes las mismas tablas, pero en **inglés** (mejor también para mantener estándares en software que luego puede escalar):

---

### **Main Tables**

#### 1. `users`
(For people who use the platform: admin, organizer, or guest)

```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'organizer', 'guest') DEFAULT 'guest',
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

#### 2. `events`
(Each event definition)

```sql
CREATE TABLE events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    organizer_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    start_datetime DATETIME NOT NULL,
    end_datetime DATETIME,
    location VARCHAR(255),
    status ENUM('draft','published','canceled') DEFAULT 'draft',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (organizer_id) REFERENCES users(user_id)
);
```

---

#### 3. `guests`
(A registry of people to invite, not necessarily system users)

```sql
CREATE TABLE guests (
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(20),
    company VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

#### 4. `invitations`
(An invitation connecting a guest to a specific event)

```sql
CREATE TABLE invitations (
    invitation_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    guest_id INT NOT NULL,
    unique_code VARCHAR(50) UNIQUE NOT NULL,
    status ENUM('pending','accepted','declined','attended','no_show') DEFAULT 'pending',
    sent_at DATETIME,
    responded_at DATETIME,
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id)
);
```

---

### **Optional Tables (to extend functionality)**

#### 5. `reminders`
(Auto reminders sent via email, SMS, or WhatsApp)

```sql
CREATE TABLE reminders (
    reminder_id INT AUTO_INCREMENT PRIMARY KEY,
    invitation_id INT NOT NULL,
    channel ENUM('email','sms','whatsapp'),
    sent_at DATETIME,
    FOREIGN KEY (invitation_id) REFERENCES invitations(invitation_id)
);
```

---

#### 6. `event_sessions`
(If an event has multiple sub-sessions, like conferences or workshops)

```sql
CREATE TABLE event_sessions (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    speaker VARCHAR(100),
    start_datetime DATETIME NOT NULL,
    end_datetime DATETIME,
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);
```

---

### 🔑 Workflow in English
1. A **user (organizer)** creates an **event**.  
2. Guests are loaded/added into the **guests** table.  
3. The system generates a **unique invitation** linking each guest to the event.  
4. Guest responds — invitation status updates (`accepted`, `declined`, etc.).  
5. Optionally send **reminders** before the event.  

---

👉 Do you want me to also add a **seating/attendance tracking table** (like `event_seats`) in case events require seat numbers or table assignments (e.g., weddings, galas, conferences)?


---

### User

Yes


---

### Assistant (gpt-5-chat)

Perfect 👌 — let's add a **seating/attendance management system**. This is very useful for events like weddings, galas, or conferences where guests must have assigned seats or tables.  

I’ll extend the schema with two more tables: one for **seats/tables** and another for **attendance tracking**.

---

### **Additional Tables**

#### 7. `event_seats`
(Defines available seats/tables at an event)

```sql
CREATE TABLE event_seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    seat_number VARCHAR(50) NOT NULL, -- e.g. "A12" or "Table-5 Seat-3"
    table_number VARCHAR(50), -- optional, for banquets/weddings
    category ENUM('regular','vip','reserved') DEFAULT 'regular',
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);
```

---

#### 8. `attendance`
(Tracks whether a guest checked in and their assigned seat/table)

```sql
CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    invitation_id INT NOT NULL,
    seat_id INT,
    checkin_time DATETIME,
    checkout_time DATETIME,
    status ENUM('not_checked_in','checked_in','left') DEFAULT 'not_checked_in',
    FOREIGN KEY (invitation_id) REFERENCES invitations(invitation_id),
    FOREIGN KEY (seat_id) REFERENCES event_seats(seat_id)
);
```

---

### 🔑 How This Works in Practice

1. **Organizer** sets up `event_seats` (e.g., 100 seats, grouped by table).  
2. When sending invitations, **guests** can either auto-assign or manually select their seats.  
3. On event day:
   - A guest **checks in** at the entrance → `attendance.checkin_time` is filled.  
   - If they leave early, `checkout_time` can be recorded.  
   - Their seat gets marked as **occupied** (`is_available = FALSE`).  

---

### Example Queries (Usage Scenarios)

✅ **Assign a guest to a seat**
```sql
UPDATE attendance
SET seat_id = 15, status = 'checked_in', checkin_time = NOW()
WHERE invitation_id = 42;
```

✅ **List free seats at an event**
```sql
SELECT seat_number, table_number
FROM event_seats
WHERE event_id = 10 AND is_available = TRUE;
```

✅ **See who sat where (event seating chart)**
```sql
SELECT g.name, g.email, es.table_number, es.seat_number
FROM attendance a
JOIN invitations i ON a.invitation_id = i.invitation_id
JOIN guests g ON i.guest_id = g.guest_id
JOIN event_seats es ON a.seat_id = es.seat_id
WHERE i.event_id = 10;
```

---

👉 Question for you:  
Would you prefer **automatic seat assignment** (system picks first available) or **manual seat selection** (guest/organizer chooses specific spots) for your use case?


---

### User

Manual seat selection


---

### Assistant (gpt-5-chat)

Perfect — **manual seat selection** 🪑✨  
That means we need to slightly adapt the schema and workflow so that the organizer (or the guest, through a web UI/app) can pick *exactly which seat/table* is assigned.  

Here’s how we can refine the system:

---

## 🔹 Updated Database Design

### 1. `event_seats`
(Exactly as before, but `is_available` is critical since manual selection will lock the seat when chosen)

```sql
CREATE TABLE event_seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    seat_number VARCHAR(50) NOT NULL, -- e.g. "A12" or "Table-5 Seat-3"
    table_number VARCHAR(50),         -- optional
    category ENUM('regular','vip','reserved') DEFAULT 'regular',
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);
```

---

### 2. `attendance`
(Links *invitations* with the **chosen seat** when the guest or organizer selects it)

```sql
CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    invitation_id INT NOT NULL,
    seat_id INT UNIQUE, -- one seat cannot be shared with multiple guests
    checkin_time DATETIME,
    checkout_time DATETIME,
    status ENUM('not_checked_in','checked_in','left') DEFAULT 'not_checked_in',
    FOREIGN KEY (invitation_id) REFERENCES invitations(invitation_id),
    FOREIGN KEY (seat_id) REFERENCES event_seats(seat_id)
);
```

Notice the `seat_id UNIQUE` constraint → ensures **one guest per seat**.

---

## 🔹 Workflow for Manual Seat Selection

1. **Event setup**: Organizer creates `event_seats` (rows, tables, categories like VIP).  

2. **Invitation issued**: Guest gets a link or code.  

3. **Seat selection phase**:  
   - Guest or organizer views a **seat map** generated from `event_seats`.  
   - Once the guest **chooses a seat**, system updates `attendance`:
     - Set `attendance.seat_id = chosen seat`.
     - Mark `event_seats.is_available = FALSE`.  

4. **Check-in on event day**: Guest arrives, organizes staff checks them in:
   - `attendance.status = 'checked_in'`.
   - `attendance.checkin_time = NOW()`.  

---

## 🔹 Example SQL Queries for Manual Selection

✅ **Show available seats for an event**
```sql
SELECT seat_id, seat_number, table_number, category
FROM event_seats
WHERE event_id = 1001 AND is_available = TRUE;
```

✅ **Assign a guest to a selected seat**
```sql
UPDATE attendance
SET seat_id = 25
WHERE invitation_id = 88;

UPDATE event_seats
SET is_available = FALSE
WHERE seat_id = 25;
```

✅ **See the seating chart**
```sql
SELECT g.name, g.email, es.table_number, es.seat_number
FROM attendance a
JOIN invitations i ON a.invitation_id = i.invitation_id
JOIN guests g ON i.guest_id = g.guest_id
JOIN event_seats es ON a.seat_id = es.seat_id
WHERE i.event_id = 1001;
```

---

✅ This setup now allows organizers to:  
- Build a **seating plan** in advance.  
- Let each guest **pick their seat manually** via a UI.  
- Lock the spot so nobody else takes it.  

---

Would you like me to also design a **sample REST API structure** (e.g., endpoints like `/api/events/{id}/seats`) so the front-end can easily implement the manual seat selection flow?


---

### User

Yep


---

### Assistant (gpt-5-chat)

Awesome 🚀 — let’s design a **REST API structure** for the **Event & Invitation System with Manual Seat Selection**.  

We’ll include all the main entities: **users, events, guests, invitations, seats, attendance**.  
This API can be built in any stack (Node.js/Express, Django, Spring Boot, etc.).  

---

# 🔹 REST API Structure

### **Authentication**
- `POST /api/auth/register` → create user (organizer/admin/guest role).
- `POST /api/auth/login` → login, returns JWT token.
- `POST /api/auth/logout` → logout.

---

### **Users**
- `GET /api/users` → list users (admin only).  
- `GET /api/users/{id}` → get user details.  
- `PUT /api/users/{id}` → update user profile.  
- `DELETE /api/users/{id}` → delete user.

---

### **Events**
- `POST /api/events` → create new event.  
- `GET /api/events` → list all events.  
- `GET /api/events/{id}` → get event details.  
- `PUT /api/events/{id}` → update event.  
- `DELETE /api/events/{id}` → delete event.  

---

### **Guests**
- `POST /api/guests` → create guest (e.g., external invitee).  
- `GET /api/guests` → list guests.  
- `GET /api/guests/{id}` → view guest.  
- `PUT /api/guests/{id}` → update guest details.  
- `DELETE /api/guests/{id}` → remove guest.

---

### **Invitations**
- `POST /api/events/{eventId}/invitations` → create invitation(s) for guests.  
- `GET /api/events/{eventId}/invitations` → list all invitations for an event.  
- `GET /api/invitations/{invitationId}` → view invitation details (guest response, status).  
- `PUT /api/invitations/{invitationId}` → update invitation status (accepted/declined).  

---

### **Seats (manual seat selection)**
- `POST /api/events/{eventId}/seats` → create seat(s) for an event (organizer sets seating chart).  
- `GET /api/events/{eventId}/seats` → list all seats for an event (with availability).  
- `GET /api/events/{eventId}/seats/available` → list only free seats.  
- `GET /api/seats/{seatId}` → view a specific seat.  
- `PUT /api/seats/{seatId}` → update seat info (e.g., mark VIP).  
- `DELETE /api/seats/{seatId}` → remove seat.

---

### **Attendance (seat assignment + check-in/out)**
- `POST /api/invitations/{invitationId}/attendance`  
  → assign a guest to a seat manually (`seat_id`).  

- `PUT /api/invitations/{invitationId}/attendance/checkin`  
  → check in guest (`status=checked_in`, `checkin_time=NOW()`).  

- `PUT /api/invitations/{invitationId}/attendance/checkout`  
  → check out guest (`status=left`, `checkout_time=NOW()`).  

- `GET /api/events/{eventId}/attendance`  
  → list attendance for event (who is seated where, check-in status).  

---

# 🔹 Example Payloads

### Create Seats (organizer sets layout)
```json
POST /api/events/1001/seats

{
  "seats": [
    { "seat_number": "A1", "table_number": "Table-1", "category": "vip" },
    { "seat_number": "A2", "table_number": "Table-1", "category": "vip" },
    { "seat_number": "B1", "table_number": "Table-2", "category": "regular" }
  ]
}
```

---

### Guest selects a seat
```json
POST /api/invitations/2001/attendance
{
  "seat_id": 15
}
```

Response (seat locked):
```json
{
  "attendance_id": 3005,
  "invitation_id": 2001,
  "seat_id": 15,
  "status": "not_checked_in"
}
```

---

### Check-in guest at event entrance
```json
PUT /api/invitations/2001/attendance/checkin
```

Response:
```json
{
  "attendance_id": 3005,
  "invitation_id": 2001,
  "seat_id": 15,
  "status": "checked_in",
  "checkin_time": "2025-09-24T21:04:00Z"
}
```

---

# 🔹 Flow Summary  

1. **Organizer** creates `event_seats` with `/api/events/{id}/seats`.  
2. **Invited guest** gets link → sees available seats via `/api/events/{id}/seats/available`.  
3. Guest selects seat → `/api/invitations/{id}/attendance` (assigns seat).  
4. At the event:
   - Staff scans code/QR → `/attendance/checkin`.  
   - If they leave → `/attendance/checkout`.  

---

👉 Would you like me to also design a **WebSocket/Event-driven flow** (for **real-time seat map updates** so organizers & guests see seat availability instantly)?


---

### User

Yep


---

### Assistant (gpt-5-chat)

Perfect 🤝 Let’s take the system one step further and add a **real-time layer** using **WebSockets (or any event-driven architecture)** so seat selection and attendance changes update instantly on the organizer’s dashboard *and* on guest-facing UI (seat map).  

---

# 🔹 Why Real-Time?
In **manual seat selection**, conflicts can happen if:
- Guest A and Guest B are looking at the same seat at the same time.  
- Guest A selects seat first → seat should immediately disappear from Guest B’s available list.  

So:  
✅ Organizer dashboard shows *live* seating chart.  
✅ Guests see available/assigned seats update instantly.  

---

# 🔹 WebSocket Channels (Event-driven)

We can define **socket namespaces or channels**:

### 1. `events/{eventId}/seats`
- **Purpose**: Real-time seat updates per event  
- **Messages published when**:  
  - Seat locked (when being selected)  
  - Seat assigned (confirmed selection)  
  - Seat freed (if guest unassigns or invitation canceled)  

**Example message:**
```json
{
  "action": "seat_assigned",
  "event_id": 1001,
  "seat_id": 15,
  "seat_number": "A1",
  "guest": { "guest_id": 501, "name": "John Doe" }
}
```

---

### 2. `events/{eventId}/attendance`
- **Purpose**: Tracks check-ins/outs  
- **Messages published when**:  
  - Guest checks in at entry  
  - Guest leaves (checkout)  
- This lets organizers see *who is inside* live.  

**Example message:**
```json
{
  "action": "guest_checked_in",
  "event_id": 1001,
  "guest_id": 501,
  "name": "John Doe",
  "seat_number": "A1",
  "checkin_time": "2025-09-24T21:04:00Z"
}
```

---

### 3. `invitations/{invitationId}`
- **Purpose**: Personal invitation updates  
- **Example**:  
  - Guest gets notification: "Your seat A1 confirmed."  
  - Organizer cancels seat → guest notified instantly.  

---

# 🔹 Workflow with Real-Time

1. **Guest opens seat map**  
   → App subscribes to `events/{eventId}/seats`.  

2. **Guest selects seat A1**:
   - Client emits: `{ action: "lock_seat", seat_id: 15 }`  
   - Server marks seat temporarily as **locked** (not yet assigned).  

3. **Guest confirms selection**:
   - Client emits: `{ action: "assign_seat", invitation_id: 2001, seat_id: 15 }`  
   - Server updates DB, flips `is_available = FALSE`, creates/upates `attendance`.  
   - Server broadcasts to all connected clients: `seat_assigned`.  

4. **Another guest sees A1 disappear instantly** from available seat list.  

5. At event check-in desk:
   - Staff scans QR → emit `checkin_guest`.  
   - Connected dashboards update: see guest name mark "Checked-In".  

---

# 🔹 Tech Options

- **WebSockets** → good if you want full-duplex communication. (Socket.IO with Node.js, Django Channels, Spring WebSocket, etc.)  
- **Server-Sent Events (SSE)** → simpler, one-way streaming from server to clients (lighter for "seat availability" updates).  
- **Pub/Sub** → for scaling: Use Redis Pub/Sub, RabbitMQ, Kafka, or NATS under the hood.  

---

# 🔹 Example WebSocket Pseudocode (Node.js + Socket.IO)

```ts
import { Server } from "socket.io";

const io = new Server(3000, { cors: { origin: "*" } });

// Seat namespace
io.of("/events").on("connection", (socket) => {
  console.log("Client connected to events namespace");

  // Join a specific event room
  socket.on("join_event", (eventId) => {
    socket.join(`event_${eventId}`);
  });

  // Handle seat selection
  socket.on("assign_seat", ({ eventId, invitationId, seatId }) => {
    // 1. DB logic: update attendance, mark seat taken
    // 2. Broadcast to all clients in this event
    io.of("/events")
      .to(`event_${eventId}`)
      .emit("seat_assigned", {
        event_id: eventId,
        seat_id: seatId,
        invitation_id: invitationId,
        status: "taken",
      });
  });

  // Handle guest check-in
  socket.on("checkin_guest", ({ eventId, invitationId, name }) => {
    io.of("/events")
      .to(`event_${eventId}`)
      .emit("guest_checked_in", {
        event_id: eventId,
        invitation_id: invitationId,
        guest_name: name,
        checkin_time: new Date().toISOString(),
      });
  });
});
```

---

# 🔹 Result

- **Guests**: They open the seat map → instantly updated with available/taken seats. No conflicts.  
- **Organizers**: Live seating chart + attendee status in real-time.  
- **Staff**: Check-ins/outs feed directly into dashboards.  

---

👉 Question for you:  
Do you want **seat locking (temporary hold)** implemented (like ticketing systems do: seat reserved for 3-5 minutes until payment/confirmation), or just **instant assignment** when a guest clicks a seat?


---

### User

I want to have various guest in a same invitation


---

### Assistant (gpt-5-chat)

Oh, excellent point 👌 — so instead of **1 invitation = 1 guest**, you want to support things like:  

- An invitation sent to a **family** (e.g. "Mr. & Mrs. Smith, plus 2 children").  
- A **corporate invitation** that includes multiple employees under one invitation code.  
- A **wedding table invitation** where one invite covers a group.  

That means the schema has to slightly evolve:  

---

# 🔹 Adjusted Schema for Group Invitations

We’ll separate the concept of an **invitation** (the "ticket") from the **attendees (people)** linked to that invitation.

---

### 1. `invitations`
Each invitation is more like a **container** (it can hold multiple guests).  

```sql
CREATE TABLE invitations (
    invitation_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    unique_code VARCHAR(50) UNIQUE NOT NULL, -- QR or URL code for group
    status ENUM('pending','accepted','declined','partial','attended') DEFAULT 'pending',
    sent_at DATETIME,
    responded_at DATETIME,
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);
```

- ✅ One invitation per family/corporate group.  
- ✅ Status can be "partial" (if some members accept, some reject).  

---

### 2. `invitation_guests`
Defines the **guests covered under an invitation**.  

```sql
CREATE TABLE invitation_guests (
    invitation_guest_id INT AUTO_INCREMENT PRIMARY KEY,
    invitation_id INT NOT NULL,
    guest_id INT NOT NULL,
    status ENUM('pending','accepted','declined','attended','no_show') DEFAULT 'pending',
    seat_id INT,
    FOREIGN KEY (invitation_id) REFERENCES invitations(invitation_id),
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
    FOREIGN KEY (seat_id) REFERENCES event_seats(seat_id)
);
```

- This allows **many guests under one invitation**.  
- Each guest from the group can have **their own seat** and **attendance record**.  

---

### 3. Updated `attendance` (optional, or merged into `invitation_guests`)
You might not need a separate `attendance` table anymore, because `invitation_guests` already tracks seat + check-in status.  
But if you still want a dedicated log of check-ins, keep `attendance` separately for auditing/history:

```sql
CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    invitation_guest_id INT NOT NULL,
    checkin_time DATETIME,
    checkout_time DATETIME,
    status ENUM('not_checked_in','checked_in','left') DEFAULT 'not_checked_in',
    FOREIGN KEY (invitation_guest_id) REFERENCES invitation_guests(invitation_guest_id)
);
```

---

# 🔹 Example Flow with Group Invitations

1. **Organizer creates** an invitation:
   - Event = 1001  
   - Invitation Code = `XYZ123`  

2. Add multiple guests under it:
   ```sql
   INSERT INTO invitation_guests (invitation_id, guest_id)
   VALUES 
   (2001, 501), -- John Smith
   (2001, 502), -- Mary Smith
   (2001, 503); -- Child Smith
   ```

3. Guest receives one invitation link (code: `XYZ123`).  
   - They open it → see names of all listed guests.  
   - They confirm attendance individually (like RSVP per guest).  

4. Seats are assigned **per guest**, not per invitation.  

---

# 🔹 Example Queries

✅ **Get all guests under one invitation:**
```sql
SELECT g.name, g.email, ig.status, es.seat_number
FROM invitation_guests ig
JOIN guests g ON ig.guest_id = g.guest_id
LEFT JOIN event_seats es ON ig.seat_id = es.seat_id
WHERE ig.invitation_id = 2001;
```

✅ **Check-in one member of the group:**
```sql
UPDATE invitation_guests
SET status = 'attended', seat_id = 15
WHERE guest_id = 501 AND invitation_id = 2001;
```

✅ **Update overall invitation status dynamically**:
- If *all guests decline* → invitation = declined  
- If *some accept, others not* → invitation = partial  
- If *all accept* → invitation = accepted  

---

# 🔹 API Structure Update

For group handling:

### Invitations
- `POST /api/events/{eventId}/invitations` → create an invitation "container".  

### Guests in an Invitation
- `POST /api/invitations/{invitationId}/guests` → add multiple guests to one invitation.  
- `GET /api/invitations/{invitationId}/guests` → list all guests linked to invitation.  
- `PUT /api/invitations/{invitationId}/guests/{invitationGuestId}` → update RSVP/seat.  

### Attendance
- `PUT /api/invitations/{invitationId}/guests/{invitationGuestId}/checkin`  
  → check in an individual from the group.  

---

# 🔹 Real-Time (WebSocket) Updates for Groups

- Channel: `events/{eventId}/invitations`  
  - Broadcast when **any guest in invitation updates their RSVP/seat/attendance**.  

Example event message:
```json
{
  "action": "guest_checked_in",
  "event_id": 1001,
  "invitation_id": 2001,
  "guest_id": 501,
  "guest_name": "John Smith",
  "seat_number": "B2"
}
```

---

✅ With this setup, you can:  
- Handle **families/corporate groups** under ONE invitation link/code.  
- Track each member’s **RSVP, seat, and check-in/out** independently.  
- Invitation status automatically reflects the group’s state.  

---

👉 Question:  
Do you want **seat selection to be per guest** in the group (each picks their own), or **block seats together** (like assigning a whole table or row to the group)?


---

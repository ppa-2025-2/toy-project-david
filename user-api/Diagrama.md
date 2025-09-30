
+--------+          +--------+
| Ticket |  ----->  | User   |
+--------+          +--------+

ManyToMany
Relationship Owner: Ticket

-> JoinTable ticket_user
join column ticket_id
inverse join column user_id
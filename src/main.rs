#[macro_use]
extern crate rocket;

mod file;
mod handlers;

#[launch]
fn rocket() -> _ {
    rocket::build().mount("/", routes![handlers::index, handlers::upload])
}

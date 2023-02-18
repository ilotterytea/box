#[macro_use]
extern crate rocket;

use std::fs::create_dir_all;

use rocket::fs::FileServer;

mod file;
mod handlers;

#[launch]
fn rocket() -> _ {
    create_dir_all("./uploaded").map_err(|e| {
        panic!(
            "Unable to create directory for uploaded files: {}",
            e.to_string()
        )
    });
    rocket::build()
        .mount("/", routes![handlers::index, handlers::upload])
        .mount("/", FileServer::from("./uploaded"))
}

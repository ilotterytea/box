use rocket::form::Form;
use rocket::fs::TempFile;
use rocket::http::Status;
use rocket::response::status::Custom;
use rocket::serde::json::Json;

use crate::file::FileData;

#[get("/")]
pub fn index() -> &'static str {
    "hello, my hateful world!"
}

#[post("/upload", data = "<file>")]
pub async fn upload(mut file: Form<TempFile<'_>>) -> Result<Json<FileData>, Custom<String>> {
    let data = FileData::new(6, &file);

    file.copy_to(format!("./uploaded/{}.{}", data.id, data.ext))
        .await
        .map_err(|e| Custom(Status::InternalServerError, e.to_string()))
        .ok();

    Ok(Json(data))
}
#[get("/")]
pub fn index() -> &'static str {
    "hello, my hateful world!"
}

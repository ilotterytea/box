use mime_guess::get_mime_extensions_str;
use rand::{self, Rng};
use rocket::fs::TempFile;
use rocket::serde::{Deserialize, Serialize};

#[derive(Deserialize, Serialize)]
#[serde(crate = "rocket::serde")]
pub struct FileData {
    pub id: String,
    pub mime: String,
    pub ext: String,
    pub key: String,
}

const CHARS: &[u8] = b"0123456789-_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

impl FileData {
    pub fn new(length: usize, file: &TempFile<'_>) -> Self {
        let mut id = String::with_capacity(length);
        let mut key = String::with_capacity(length * 2);
        let mut rng = rand::thread_rng();

        for _ in 0..length {
            id.push(CHARS[rng.gen::<usize>() % CHARS.len()] as char);
        }

        for _ in 0..length * 2 {
            key.push(CHARS[rng.gen::<usize>() % CHARS.len()] as char);
        }

        let mime = &file.content_type().unwrap().to_string();
        let ext_vec = get_mime_extensions_str(mime.as_ref()).unwrap();

        let ext = ext_vec[ext_vec.len() - 1].to_string();

        Self {
            id,
            mime: mime.to_owned(),
            ext,
            key,
        }
    }
}

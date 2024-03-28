var adminDB = db.getSiblingDB('admin');
adminDB.auth('root', '123456');
db = db.getSiblingDB('trevally_challenge');
db.createCollection('users');
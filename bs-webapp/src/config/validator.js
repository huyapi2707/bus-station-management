const usernameMinLength = 5;
const emailMinLength = 5;
const passwordMinLength = 3;

const validateUsername = (username) => {
  if (
    username === null ||
    username === '' ||
    username.length < usernameMinLength
  )
    return `Username must have at least ${usernameMinLength} characters`;

  return null;
};

const validateEmail = (email) => {
  if (email === null || email === '' || email.length < emailMinLength)
    return `Email must have at least ${emailMinLength} characters`;
  if (
    !email
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
      )
  )
    return `Email is incorect format`;
  return null;
};

const validatePassword = (password) => {
  if (
    password === null ||
    password === '' ||
    password.length < passwordMinLength
  )
    return `Password must have at least ${password} characters`;

  return null;
};

export {validateEmail, validateUsername, validatePassword};

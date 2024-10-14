import React, {useState} from 'react'
import { addUser } from '../Services/User'
import { nicknameCheck, loginCheck, emailCheck, getUser } from '../Services/User'
import { useNavigate } from 'react-router-dom'
import 'bootstrap'

const EnterComponent = ({ onChange }) => {

    const [nickname, setNickname] = useState('')
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')
    const [email, setEmail] = useState('')
    const level = 0;

    const navigator = useNavigate()

    function checkForData(e) {
        e.preventDefault();

        const user = { nickname, login, password, email, level }

        Promise.all([nicknameCheck(nickname), loginCheck(login), emailCheck(email)])
            .then(([nicknameResponse, loginResponse, emailResponse]) => {
                if (nicknameResponse.data == true && loginResponse.data == true && emailResponse.data == true) {
                    addUser(user).then((response) => {
                        onChange(response.data.id);
                    })
                    navigator('/menu');
                } else {
                    if (nicknameResponse.data == false) {
                        alert('Пользователь с таким никнеймом уже существует!!!');
                        console.log('Пользователь с таким никнеймом уже существует!!!');
                    }
                    if (loginResponse.data == false) {
                        alert('Пользователь с таким логином уже существует!!!');
                        console.log('Пользователь с таким логином уже существует!!!');
                    }
                    if (emailResponse.data == false) {
                        alert('Пользователь с такой почтой уже существует!!!');
                        console.log('Пользователь с такой почтой уже существует!!!');
                    }
                    e.preventDefault();
                }
            });
    }

    async function authorization(e) {
        e.preventDefault();
      
        const forAuthorization = { login, password };
        console.log(forAuthorization);
      
        try {
          const response = await getUser(forAuthorization);
          if (response.status != 404) {
            onChange(response.data.id);
            navigator('/menu');
          }
        } catch (error) {
          if (error.response && error.response.status === 404) {
            alert('Пользователь не найден!');
          }
        }
      }

    return (
        <div className='EnterMain'>
            <div className='container container-fluid bg-secondary w-25 rounded-3 text-center'>
                <div className='BlockTitle'>
                    Добро пожаловать!
                </div>
                <div className='row justify-content-md-center mb-4 pt-4'>
                    <button type="button" className="btn btn-light w-50" data-bs-toggle="modal" data-bs-target="#registrationModal">
                        Регистрация
                    </button>
                </div>
                <div className='row justify-content-md-center pb-5'>
                    <button type="button" className="btn btn-light w-50" data-bs-toggle="modal" data-bs-target="#autorizationModal">
                        Вход
                    </button>
                </div>
            </div>
            <div className="modal fade" id="registrationModal" tabindex="-1" aria-labelledby="registrationModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="registrationModalLabel" style="align-content: center;">Регистрация пользователя</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form>
                                <div className="mb-3">
                                    <label for="nickname" className="form-label">Никнейм</label>
                                    <input type="text" value={nickname} className="form-control" onChange={(e) => setNickname(e.target.value)} id="nickname" required />
                                </div>
                                <div className="mb-3">
                                    <label for="login" className="form-label">Логин</label>
                                    <input type="text" value={login} className="form-control" onChange={(e) => setLogin(e.target.value)} id="login" required />
                                </div>
                                <div className="mb-3">
                                    <label for="password" className="form-label">Пароль</label>
                                    <input type="password" value={password} className="form-control" onChange={(e) => setPassword(e.target.value)} id="password" required />
                                </div>
                                <div className="mb-3">
                                    <label for="email" className="form-label">Почта</label>
                                    <input type="email" value={email} className="form-control" onChange={(e) => setEmail(e.target.value)} id="email" required />
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Закрыть</button>
                            <button type="button" className="btn btn-success" data-bs-dismiss="modal" onClick={checkForData}>Зарегистрироваться</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="autorizationModal" tabindex="-1" aria-labelledby="autorizationModal" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="autorizationModal" style="align-content: center;">Авторизация пользователя</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="mb-3">
                                    <label for="login" class="form-label">Логин</label>
                                    <input type="text" class="form-control" onChange={(e) => setLogin(e.target.value)} id="login" required />
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Пароль</label>
                                    <input type="password" class="form-control" onChange={(e) => setPassword(e.target.value)} id="password" required />
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Закрыть</button>
                            <button type="button" class="btn btn-success" onClick={authorization} data-bs-dismiss="modal">Авторизоваться</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default EnterComponent
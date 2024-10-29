import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import image from '/src/assets/standart-user.svg'
import { getUserById } from '../Services/User'
import { updateNickname } from '../Services/User'
import { updateAvatar } from '../Services/User'
import { getUserAvatar } from '../Services/User'
import { updateEmail } from '../Services/User'
import { listOfFriends } from '../Services/User'
import Stomp from 'stompjs'
import SockJS from "sockjs-client/dist/sockjs"
import 'bootstrap'

const GameMenuComponent = () => {

    const [friends, setFriends] = useState([]);

    const [avatar, setAvatar] = useState(null);

    const [nickname, setNickname] = useState('');

    const [newNickname, setNewNickname] = useState('');

    const [content, setContent] = useState('');

    const [email, setEmail] = useState('');

    const navigator = useNavigate()

    useEffect(() => {
        getUserById(parseInt(localStorage.getItem('userId'))).then((response) => {
            setNickname(response.data.nickname);
        });
        if (avatar == null) {
            getUserAvatar(parseInt(localStorage.getItem('userId'))).then((response) => {
                if (response.status != 404) {
                    const ImageUrl = URL.createObjectURL(response.data);
                    setAvatar(ImageUrl);
                }
            })
        };
    });

    const mouseClickGame = () => {
        setContent(
            <>
                <div className='container mt-5 border w-50 text-center'>
                    <div className='col'>
                        <div className='row'>
                            <button className='btn btn-outline-danger' onClick={mouseClickRandomEnemy}>Случайный противник</button>
                        </div>
                        <div className='row mt-3'>
                            <button className='btn btn-outline-success'>Найти комнату</button>
                        </div>
                    </div>
                </div>
            </>
        );
    };

    const mouseClickRandomEnemy = () => {
        setContent(
            <>
                <h1>Поиск противника...</h1>
            </>
        );
    };

    const mouseClickCreateRoom = () => {
        setContent(
            <h1>Создание комнаты</h1>
        );
    };

    const mouseClickFriends = () => {
        listOfFriends(parseInt(localStorage.getItem('userId'))).then((response) => {
            setFriends(response.data);
        }).catch(error => {
            console.error(error);
        });
        setContent(
            <>
                <div className='d-flex flex-row justify-content-center'>
                    <h1 className='pt-3 title'>Друзья</h1>
                    <div className='addFriendDiv'>
                        <button className='image-button' onClick={mouseClickAddFriend}><img className='addFriend' src="src/assets/add-friend.svg" alt="Добавить друга" /></button>
                    </div>
                </div>
            </>
        )
    };

    useEffect(() => {
        if (friends.length > 0) {
            setContent(
                <>
                    <div className='d-flex flex-row justify-content-center'>
                        <h1 className='pt-3 title'>Друзья</h1>
                        <div className='addFriendDiv'>
                            <button className='image-button' onClick={mouseClickAddFriend}><img className='addFriend' src="src/assets/add-friend.svg" alt="Добавить друга" /></button>
                        </div>
                    </div>
                    <br />
                    <div className="d-flex flex-column" style={{ paddingLeft: '110px', paddingRight: '110px' }}>
                        {friends.map(friend => (
                            <div key={friend.id} className="mb-3 d-flex justify-content-between align-items-center rounded-3 p-2 ps-3" style={{backgroundColor: '#caddd9', boxShadow: '5px 5px 10px rgba(0, 0, 0, 0.2)'}}>
                                <span style={{ fontSize: '23px', fontWeight: 'bold' }}>{friend.nickname}</span>
                                <div className="d-flex">
                                    <button className='btn btn-outline-success'>Написать сообщение</button>
                                    <button className='btn btn-outline-danger' style={{ marginLeft: '15px' }}>Удалить</button>
                                </div>
                            </div>
                        ))}
                    </div>
                </>
            );
        }
    }, [friends]);

    const mouseClickAddFriend = () => {
        setContent(
            <>
                <h1>Поиск друга</h1>
            </>
        )
    }

    const mouseClickCreateShop = () => {
        setContent(
            <h1>Магазин</h1>
        );
    };

    const mouseClickMessages = () => {
        setContent(
            <>
                <h1>Сообщения</h1>
            </>
        );
        const socket = new SockJS('http://localhost:8080/websocket');
        const client = Stomp.over(socket);

        client.connect({}, () => {
            console.log('Подключение к серверу!!!');
        });
    }

    const saveSettings = () => {
        if (newNickname != '') {
            updateNickname(newNickname, parseInt(localStorage.getItem('userId'))).then((response) => {
                setNickname(response.data.nickname);
            });
        }

        if (avatar != null) {
            const blob = new Blob([avatar], { type: avatar.type });

            const formData = new FormData();
            formData.append('avatar', blob, avatar.name);

            updateAvatar(formData, parseInt(localStorage.getItem('userId')));

            const ImageUrl = URL.createObjectURL(blob);
            setAvatar(ImageUrl);
        }
        if (email != '') {
            updateEmail(email, parseInt(localStorage.getItem('userId')));
        }
    }

    const mouseClickExit = () => {
        setAvatar(null);
        setNickname(null);
        setFriends([]);
        navigator('/');
    };

    return (
        <div className='GameMenu'>
            <div className='container border bg-light h-100'>
                <div className='row text-center'>
                    <div className='col-2 pt-4 MenuColumns'>
                        <button className='btn btn-outline-dark ps-4 pe-4' onClick={mouseClickGame}>Играть</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickCreateRoom}>Создать комнату</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickMessages}>Сообщения</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickFriends}>Друзья</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickCreateShop}>Магазин</button>
                    </div>
                    <div className='col MenuColumns'>
                        {content}
                    </div>
                    <div className='col-2 pt-4 MenuColumns'>
                        <div className='rounded Avatar' style={{ backgroundImage: `url(${avatar || image})`, backgroundSize: 'contain', backgroundRepeat: 'no-repeat' }}></div>
                        <p className='mt-2 text-center Nickname'>{nickname}</p>
                        <br />
                        <button className='btn btn-outline-dark ps-4 pe-4' data-bs-toggle="modal" data-bs-target="#settingsModal">Настройки</button>
                        <br />
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickExit}>Выход</button>
                    </div>
                </div>
            </div>
            <div className="modal fade" id="settingsModal" tabindex="-1" aria-labelledby="settingsModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="settingsModalLabel" style="align-content: center;">Настройки</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form>
                                <div className="mb-3">
                                    <label for="nickname" className="form-label">Никнейм</label>
                                    <input type="text" value={newNickname} className="form-control" onChange={(e) => setNewNickname(e.target.value)} id="nickname" required />
                                </div>
                                <div className="mb-3">
                                    <label for="email" className="form-label">Электронная почта</label>
                                    <input type="text" value={email} className="form-control" onChange={(e) => setEmail(e.target.value)} id="email" required />
                                </div>
                                <div className="mb-3">
                                    <label for="Avatar" className="form-label">Фото профиля</label>
                                    <input type="file" accept=".jpg, .jpeg, .png" className="form-control" onChange={(e) => setAvatar(e.target.files[0])} id="Avatar" required />
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Закрыть</button>
                            <button type="button" className="btn btn-success" data-bs-dismiss="modal" onClick={saveSettings}>Сохранить</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default GameMenuComponent
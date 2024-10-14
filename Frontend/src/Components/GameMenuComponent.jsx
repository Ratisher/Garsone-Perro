import React, { useState, useEffect } from 'react'
import image from '/src/assets/standart-user.svg'
import { getUserById } from '../Services/User'
import 'bootstrap'

const GameMenuComponent = ({ id }) => {

    const [Nickname, setNickname] = useState('');

    const [content, setContent] = useState('');

    useEffect(() => {
        console.log(id);
        if (id != null || id != undefined) {
            localStorage.setItem('userId', id);
        }
        getUserById(parseInt(localStorage.getItem('userId'))).then((response) => {
            console.log(parseInt(localStorage.getItem('userId')));
            console.log(response.data.nickname);
            setNickname(response.data.nickname);
        });
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
        setContent(
            <h1>Друзья</h1>
        );
    };

    const mouseClickCreateShop = () => {
        setContent(
            <h1>Магазин</h1>
        );
    };


    return (
        <div className='GameMenu'>
            <div className='container border bg-light h-100'>
                <div className='row text-center'>
                    <div className='col-2 pt-4 MenuColumns'>
                        <button className='btn btn-outline-dark ps-4 pe-4' onClick={mouseClickGame}>Играть</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickCreateRoom}>Создать комнату</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickFriends}>Друзья</button>
                        <button className='btn btn-outline-dark ps-4 pe-4 mt-4' onClick={mouseClickCreateShop}>Магазин</button>
                    </div>
                    <div className='col MenuColumns'>
                        {content}
                    </div>
                    <div className='col-2 pt-4 MenuColumns'>
                        <div className='rounded Avatar  ' style={{ backgroundImage: `url(${image})`, backgroundSize: 'cover' }}></div>
                        <p className='mt-2 text-center Nickname'>{Nickname}</p>
                        <br />
                        <button className='btn btn-outline-dark'>Настройки</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default GameMenuComponent
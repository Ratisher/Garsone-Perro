import React from 'react'
import Phaser from 'phaser'

const GameMapGarsone = () => {

    const game = new Phaser.Game({
        type: Phaser.AUTO,
        width: 1900,
        height: 1000,
        parent: 'game',
        physics: {
            default: 'arcade',
            arcade: {
                gravity: { y: 0 } // Отключаем гравитацию
            }
        },
        scene: {
            preload: preload,
            create: create,
            update: update
        }
    });
    function preload() {
        // Загрузка ресурсов: изображения, спрайты, звуки
        this.load.image('fathergarson', 'src/assets/FatherGarson.jpg');
    }

    function create() {

        // Создание элементов игры
        this.player = this.physics.add.sprite(100, 100, 'fathergarson'); // Создаем игрока
        this.player2 = this.physics.add.sprite(400,400, 'fatherperro');
        this.player.setCollideWorldBounds(true); // Препятствуем выходу игрока за пределы карты
        this.cursors = this.input.keyboard.createCursorKeys(); // Создаем управление стрелками
        this.input.keyboard.on('keydown-N', zoomOut, this);
        this.input.keyboard.on('keydown-M', zoomInside, this);
    }

    function zoomInside(){
        // Получаем камеру игры
        const camera = this.cameras.main;
      
        // Увеличиваем масштаб камеры на 0.1
        camera.zoom -= 0.1;
      
        // Ограничиваем максимальный масштаб камеры, например, до 2
        camera.zoom = Math.max(camera.zoom, 0.5);
    }

    function zoomOut() {
        // Получаем камеру игры
        const camera = this.cameras.main;
      
        // Увеличиваем масштаб камеры на 0.1
        camera.zoom += 0.1;
      
        // Ограничиваем максимальный масштаб камеры, например, до 2
        camera.zoom = Math.min(camera.zoom, 2);
      }

    function update() {
        // Обновление состояния игры
        if (this.cursors.left.isDown) {
            this.player.setVelocityX(-150);
        } else if (this.cursors.right.isDown) {
            this.player.setVelocityX(150);
        } else {
            this.player.setVelocityX(0);
        }

        if (this.cursors.up.isDown) {
            this.player.setVelocityY(-150);
        } else if (this.cursors.down.isDown) {
            this.player.setVelocityY(150);
        } else {
            this.player.setVelocityY(0);
        }
    }

    return (
        <div></div>
    )
}

export default GameMapGarsone
# 😛 Shake it ! 😛
activity 에 SensorEventListener 인터페이스를 상속하면서 onSensorChange와 onAccuracyChanged 메서드로 구현한다.  
또한 메모리 손실 방지를 위해 실행 됬을 때 onResume, 나가려고 할때 센서 메니저를 꺼줘야 하므로 onPause로 구현.

✔ 흔들림이 감지 될 때 마다 1초동안 잘자 이미지로 변경! 😀

<p align="center">
  <img src="https://github.com/eunjee001/shakeit/assets/57342856/8f3be6bc-f0f3-48be-a744-0103c3f2688a">
</p>

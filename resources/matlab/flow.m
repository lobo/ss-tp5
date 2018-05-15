clear all
clc

QT_EDESUR=readtable('flow1.txt');
QT=table2array(QT_EDESUR);

x = QT(:,1);
y = QT(:,2);

figure;
plot(x,y,'.');
title 'Caudal - Simulación 1';
xlabel('Tiempo [s]')
ylabel('Caudal')

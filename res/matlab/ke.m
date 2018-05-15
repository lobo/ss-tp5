clear all
clc

QT_EDESUR=readtable('results1.txt');
QT=table2array(QT_EDESUR);

x = QT(:,1);
y = QT(:,2);

figure;
plot(x,y,'.');
title 'Kinetic energy';
xlabel('Delta t [s]')
ylabel('Kinetic Energy [J]')

x = x(30:end);
y = y(30:end);

figure;
semilogy(x,y);
%axis([2 20 0.0001 0.005]);
%axis 'auto y'
title 'Kinetic energy';
xlabel('Delta t [s]')
ylabel('Kinetic Energy [J]')

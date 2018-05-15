clc;clear; close all;
%% Plot teorico
g = 9.8196; % [m/s^2]
R_min = 0.01; % [m] 
R_max = 0.015; % [m]
m = 0.01; % [kg]
N = 370;
W = 0.3;
H = 1;
d = [ 0.15 0.19 0.23 0.27 ];
k = 4; % Numero a dibujar
Q_teo = (N/(W*H))*sqrt(g)*((d-k*((R_min+R_max)/2)).^(3/2));
h1 = plot(d,Q_teo,'-o','LineWidth',2);
%% Plot experimental
hold on
Q_exp = [121.0294118 196.2745098 304.9673203 561.6339869];
% return
h2 = plot(d,Q_exp,'-o','LineWidth',2);
    % Plot error
    errv1 = [3.862733 4.2590113 5.396017632 6.994990976];   
    errorbar(d,Q_exp,errv1,'k*'); 
%% Plot characteristics
hold on
grid on; grid minor;
str1 = sprintf('Ley de Beverloo con k = %.2f',k);
legend(str1,'Datos experimentales')
title('Ley de Beverloo: valores teoricos vs. experimentales')
xlabel('Ancho del drenaje (d)'); ylabel('Caudal Q(d)'); 
ax=gca;
ax.XTick=ax.XLim(1):0.01:ax.XLim(2);
ax.YTick=ax.YLim(1):50:ax.YLim(2);


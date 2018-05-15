clc;clear; close all;
%% Plot teorico
g = 9.8196; % [m/s^2]
R_min = 0.01; % [m] 
R_max = 0.015; % [m]
m = 0.01; % [kg]
N = 370;
W = 0.3;
H = 0.6175;
d = [ 0.15 0.19 0.23 0.27 ];
Qs = [121.0294118 166.0784 196.2745098 304.9673203];
%% C�lculo de Qb
k = 0:0.001:10;
Qb = zeros(length(k),4);
for i=1:length(k)
    for j=1:4
        Qb(i,j) = (N/(W*H))*sqrt(g)*((d(j)-k(i)*((R_min+R_max)/2)).^(3/2));
    end
end

%% C�lculo de error cuadr�tico medio
err_med = zeros(length(k),1);
for i=1:length(k)
    err_med(i) = immse(Qs,Qb(i,:));
end
%% Plot 
h1 = plot(k,err_med,'LineWidth',2);

%% Plot characteristics
hold on
grid on; grid minor;
title('Error cuadr�tico medio en funci�n de k')
xlabel('Valores de k entre 0 y 10'); ylabel('Error cuadr�tico medio'); 
ax=gca;
ax.XTick=ax.XLim(1):0.5:ax.XLim(2);
ax.YTick=ax.YLim(1):25000:ax.YLim(2);


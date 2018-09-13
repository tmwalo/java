# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: tmwalo <marvin@42.fr>                      +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2018/07/16 11:24:27 by tmwalo            #+#    #+#              #
#    Updated: 2018/07/16 13:15:07 by tmwalo           ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

CLASS = com/gmail/vuyotm/Aircraft.class \
		com/gmail/vuyotm/AircraftFactory.class \
		com/gmail/vuyotm/Baloon.class \
		com/gmail/vuyotm/Coordinates.class \
		com/gmail/vuyotm/Flyable.class \
		com/gmail/vuyotm/Helicopter.class \
		com/gmail/vuyotm/JetPlane.class \
		com/gmail/vuyotm/Simulator.class \
		com/gmail/vuyotm/Tower.class \
		com/gmail/vuyotm/WeatherProvider.class \
		com/gmail/vuyotm/WeatherTower.class \

EXCEPTIONS_CLASS = com/gmail/vuyotm/custom_exceptions/CoordinateNotPositiveException.class \
					com/gmail/vuyotm/custom_exceptions/InvalidAircraftData.class \
					com/gmail/vuyotm/custom_exceptions/InvalidSimulationIterations.class \

all:
	find . -name *.java > sources.txt
	javac -sourcepath . @sources.txt

clean:
	@echo "Clean class files"
	@/bin/rm $(CLASS)
	@echo "Clean exceptions class files"
	@/bin/rm $(EXCEPTIONS_CLASS)

run:
	java com.gmail.vuyotm.Simulator test_files/empty.txt

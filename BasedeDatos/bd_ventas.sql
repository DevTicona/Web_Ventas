/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL
 Source Server Type    : MySQL
 Source Server Version : 100432
 Source Host           : localhost:3306
 Source Schema         : bd_ventas

 Target Server Type    : MySQL
 Target Server Version : 100432
 File Encoding         : 65001

 Date: 28/05/2024 00:10:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clientes
-- ----------------------------
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `correo` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `celular` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of clientes
-- ----------------------------
INSERT INTO `clientes` VALUES (1, 'Nicolas Rojas', 'nico@mail.com', '77712345');
INSERT INTO `clientes` VALUES (6, 'Halee Kirby', 'tiam@tempor.com', '72012345');
INSERT INTO `clientes` VALUES (7, 'Marco Perez', 'marco@mail.com', '71526789');
INSERT INTO `clientes` VALUES (8, 'Pedro Marquez', 'peter@mail.com', '72054578');
INSERT INTO `clientes` VALUES (9, 'Juan de Arco', 'juanita@mail.com', '77112456');
INSERT INTO `clientes` VALUES (10, 'Luis Callejas', 'lucho@mial.com', '77122456');
INSERT INTO `clientes` VALUES (16, 'Marcelo Martins', 'marcelo@mail.com', '70012345');
INSERT INTO `clientes` VALUES (18, 'Rodrigo', 'rodri@mail.com', '73434443');

-- ----------------------------
-- Table structure for productos
-- ----------------------------
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `descripcion` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of productos
-- ----------------------------
INSERT INTO `productos` VALUES (1, 'iPhone 4s', 'Que viene desde Apple', 200);
INSERT INTO `productos` VALUES (2, 'iPhone 5', 'iPhone 5 from Apple', 150);
INSERT INTO `productos` VALUES (3, 'iPhone 5s', 'iPhone 5s is too expensive.', 300.8);
INSERT INTO `productos` VALUES (4, 'iPad Air', 'iPad Air is thin like samurai!', 250);
INSERT INTO `productos` VALUES (5, 'HTC One', 'Best of 2013', 250);
INSERT INTO `productos` VALUES (6, 'God', 'God is not on sale. Sorry.', 132);
INSERT INTO `productos` VALUES (7, 'Lenovo 2020', 'This sword is so sharp that it can slice itself. Good for slicing jellies.', 2000);
INSERT INTO `productos` VALUES (8, 'Latitude e6420', 'Built by ultimate killing machines. For ultimate killing hobies.', 50);
INSERT INTO `productos` VALUES (9, 'Toshiba xd456', 'This jacket could save you from heart attack. And maybe heartbreaks.', 50000);
INSERT INTO `productos` VALUES (10, 'Dell vostro 456', 'This helps you brighten things in the dark.', 650);
INSERT INTO `productos` VALUES (73, 'TV', 'LG de 50 pulgadas', 4000.5);

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `apellidos` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `correo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES (1, 'admin', 'admin', 'admin@gmail.com', 'd033e22ae348aeb5660fc2140aec35850c4da997');
INSERT INTO `usuarios` VALUES (2, 'Primo', 'Ticona', 'ticona@gmail.com', '0b78a9feaf2ed744432c32428b47eb918632282a');
INSERT INTO `usuarios` VALUES (4, 'Ricardo', 'Paredes Tapia', 'rick@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964');

-- ----------------------------
-- Table structure for ventas
-- ----------------------------
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producto_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of ventas
-- ----------------------------
INSERT INTO `ventas` VALUES (1, 1, 10, '2019-02-08');
INSERT INTO `ventas` VALUES (4, 2, 6, '2014-07-08');
INSERT INTO `ventas` VALUES (18, 9, 9, '2024-09-09');

SET FOREIGN_KEY_CHECKS = 1;
